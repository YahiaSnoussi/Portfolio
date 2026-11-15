---
name: ascend
description: Ascend — Yahia's Motion-style productivity app (to-do list with auto-scheduler, priorities, goals, projects, habits, day/week/month reviews, challenges/coach). Spring Boot 3.4 + Angular 21 + PostgreSQL, in OneDrive/Bureau/Ascend. Load whenever working on this project: new feature, bug, launching it locally, seed data, Google sign-in, Google Calendar sync, mobile reminders, deploy, or resuming the previous session.
---

# Ascend — Project skill

You are picking up **Ascend**, Yahia's personal productivity app, started 2026-11-08. He asked for it as
"clone Motion": to-dos, prioritisation, goals, project monitoring, rating his days/weeks/months, a habit tracker
and "challenge me". He uses it himself first, but it **must stay multi-user** (every query is scoped by user id).
Treat what follows as the reference; check the real code (`git status`, files) before saying something exists.

Path: `C:\Users\hayou\OneDrive\Bureau\Ascend` (note **OneDrive**\Bureau).
Repo: github.com/YahiaSnoussi/ascend (private), branch `main`. There is no `gh` CLI on this PC: use the GitHub API
with the token from `git credential fill`.
Language: **UI and code in English**. Yahia writes in French or English, so reply in the language he uses.

---

## 1. RUN IT (one go)

| Part | Command | Port |
|---|---|---|
| PostgreSQL | local PG 18 Windows service, DB `ascend_db`, postgres/postgres | 5432 |
| API | `cd backend && mvn spring-boot:run` (Flyway creates the schema) | **8095** |
| Front | `cd frontend && npm start` (proxy `/api` → 8095 via `proxy.conf.json`) | **4300** |
| Login | http://localhost:4300 with `test1@ascend.local` / `password123` (holds Yahia's real data) | — |
| psql | `"/c/Program Files/PostgreSQL/18/bin/psql.exe" -U postgres -h localhost -d ascend_db` (PGPASSWORD=postgres) | — |

Steps:
1. **First check what is already running**: `netstat -ano | grep -E ":(8095|4300|5432) " | grep LISTEN`.
   The servers often survive between sessions; starting them again gives "Port already in use".
   Restart the API with `taskkill //F //PID <pid on :8095>`.
2. Start whatever is missing in the background, with logs going to `backend.log` / `frontend.log` at the repo root.
3. Check: `curl -s -o /dev/null -w "%{http_code}" http://localhost:4300/` should return 200, and a POST to
   `/api/auth/login` with the test account should return a token.
4. Give him the URL and the login.

- Reseed (**wipes the DB**: all users and data): `bash seed/seed.sh` while the API is running. `seed/` is gitignored
  (it mentions work projects).
- Secrets go in `backend/.env` (gitignored, loaded by `spring.config.import`): `GOOGLE_CLIENT_ID`, `GOOGLE_CLIENT_SECRET`, `MAIL_HOST/PORT/USERNAME/PASSWORD/FROM`. Restart the API after editing it.
- Google sign-in button and Calendar connect stay hidden/disabled until the Google keys are set (README § Google has the console steps).
- Without `MAIL_HOST` emails are saved as HTML in `backend/mail-outbox/` (gitignored). Preview: `GET /api/notifications/daily-email/preview?date=YYYY-MM-DD`.
- Python is not installed → use Node or bash for scripts.
- Screenshots: Node + CDP over headless Chrome; in Git Bash prefix with `MSYS_NO_PATHCONV=1`, otherwise page paths
  get mangled into `C:/Program Files/Git/...`. The Chrome extension is unreliable on this PC.
- Front check: `npx ng build --configuration development --output-path <scratchpad>/ngcheck`. Back check: `mvn -q -o compile`.

---

## 2. ARCHITECTURE

### Backend — `backend/` (Spring Boot 3.4, Java 21, package `com.ascend`, packaged by feature)
- `task/`: `Task`, `CalendarEvent` (weekly repeat: `repeat_days`, `repeat_until`), `EventService`,
  **`SchedulerService`** (places open tasks in free work hours: ASAP first, then hard deadlines due within 3 days,
  then priority, then deadline; never overlaps events; re-plans after every change; flags "at risk"), `TaskController`.
- `goal/`, `project/` (progress weighted by duration vs time elapsed, health, forecast), `habit/` (daily or N/week,
  streaks, heatmap), `review/` (day/week/month 1–10 + energy, with the period's real numbers), `challenge/`
  (generated from his history + custom ones, XP/levels), `dashboard/` (Today page + coach insights),
  `user/` (settings: timezone, work hours/days, email prefs), `security/` (JWT, Google ID token), `common/` (`TokenCipher` AES-GCM for stored Google tokens).
- `google/` **`GoogleCalendarService`**: OAuth code flow (scope `calendar`, redirect `PUBLIC_URL/api/google/callback`, HMAC-signed state), import of busy timed events from calendars shown in Google into `events` with `source=GOOGLE` (read-only, replaced each sync, past 14 days kept), push of planned tasks to an "Ascend" calendar (diff by private extended property `ascendTaskId` + `ascendHash`, pop-up reminder), triggered by `SchedulerService.PlanChanged` (coalesced 3 s, single thread) and a 10-min job. Connecting also links `googleSub` and sets `notifyEmail`.
- `notify/`: `MailService` (SMTP or outbox), `NotificationService` (1-min tick: daily plan at `dailyEmailTime` in the user's zone, 3 h grace, once a day via `last_daily_email`; reminders `reminderMinutes` before tasks and one-off events, dedup in `reminders_sent`), `NotificationController`.
- Config `application.yml`: `ddl-auto: none`, **Flyway** (`db/migration/V1__init.sql`, `V2__google_and_recurring_events.sql`, `V3__google_calendar_and_emails.sql`),
  `hibernate.jdbc.time_zone: UTC` (the API runs in UTC: before that, TIME work hours were shifted by the PC's offset).
  Env: `PORT`, `DB_URL`, `DB_USER`, `DB_PASSWORD`, `JWT_SECRET`, `JWT_DAYS`, `GOOGLE_CLIENT_ID`.

### Frontend — `frontend/` (Angular 21, standalone, signals, **zoneless**)
- `core/`: `api.service.ts`, `auth.service.ts`, `models.ts`, `util.ts`.
- `pages/`: today (quick add `Call the bank 15m !high`), tasks, calendar (week view), projects, project-detail,
  goals, habits, reviews, challenges, settings, login. Light/dark themes, bottom navigation on phones.
- Browser dates show in the French locale.

### Deploy
`docker-compose.yml` (Postgres + API + Caddy with automatic HTTPS), `.env` from `.env.example`
(DOMAIN, DB_PASSWORD, JWT_SECRET). Nothing has been deployed yet.

### Docs
`docs/Ascend-explained-simply.pptx` (deck for kids) and `docs/diagrams/` (archify ERD; see the archify-on-Windows memory).

---

## 3. RULES

- **Multi-user**: every repository query is filtered by the signed-in user id. Never add a query that isn't.
- **Flyway**: a schema change goes in a new `V4__...sql`. **Never edit V1–V3.**
- Keep the API in UTC; convert with the user's timezone setting.
- Commit with clear messages and push to `main` when Yahia asks.

---

## 4. STATE AND NEXT STEPS

- Commits: `1015e8c` v1, `2996878` Google sign-in + repeating events + kid deck, `a32a8e1` ignore Office lock files.
- Real data seeded on 2026-11-10: 5 goals, 11 projects, 4 habits (swimming Mon/Wed/Fri, LeetCode, music, learning),
  a "Travail" event Mon–Fri 08–18, planning window 08–22 every day (so tasks land on evenings and weekends).
- **Still to confirm with him**: habit frequencies, the 22:00 end of day, swimming time (no event yet).
- 2026-11-15 (not committed yet): Google Calendar two-way sync, daily plan email at 08:00 in the user's zone, email reminders, Settings sections, 10 unit tests green. Tested with the outbox (reminder + digest OK); **real Google and SMTP not tested yet: waiting for Yahia's OAuth client + Gmail app password** in `backend/.env`. His account email is `test1@ascend.local`, so he must set "Send emails to" (or connect Google, which fills it).
- All 67 seeded tasks are DONE: nothing open to plan right now.
- Next ideas: push notifications (PWA), Outlook sync, recurring tasks.
- At the end of a session: update this skill (§4) and the `project_ascend.md` memory.
