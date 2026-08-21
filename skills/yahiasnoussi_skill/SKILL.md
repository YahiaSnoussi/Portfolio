---
name: yahiasnoussi_skill
description: Personal context about Yahia Snoussi — background, active projects, open tasks, and learning goals. Load this at the start of a session to pick up where any previous session left off, regardless of machine.
---

# Yahia Snoussi — Personal Context

## Background

- Senior Full-Stack Engineer by day, music producer by night.

## Active Projects

### Raffaella
Premium bio-cosmetics e-commerce brand (Tunisian market + international). Spring Boot + Angular, MySQL. Past MVP — coupons, verified-purchase reviews, order email/WhatsApp notifications, TND currency, admin nav-visibility controls, and carousel/branding admin. The entire accumulated feature set was committed and pushed to `origin/master` for the first time 2026-08-21 (had been sitting uncommitted since 2026-07-30).
- Related skill: `/raffaella-brand` — full brand/tech context (stack, architecture, deployment gaps), kept in sync with the code.

### OnBoarding
Internal company project (self-initiated, not yet an official company deliverable).
- A hybrid guide for new hires, includes Scrum tooling.
- Backend migrated to hexagonal architecture (5 bounded contexts).
- Related skills: `/onboarding` — project overview skill. `/onboarding-details` — full functional analysis, kept in sync with the migration.

### Bonplan
App de bons plans locaux pour le Grand Tunis. Spring Boot + Angular 21 + Kafka + Redis + Postgres, IA via Claude API.
- Étape 1 (fondations) complète. No dedicated skill yet — context lives in project memory only.

## Related Skills

Quick index of other project-scoped skills to load for deeper context on a given project:
- `/raffaella-brand` — Raffaella e-commerce, full project skill.
- `/onboarding` — OnBoarding Hub, project skill.
- `/onboarding-details` — OnBoarding Hub, full functional analysis.

## Currently Learning

- AI / Claude AI
- Automation with n8n
- LeetCode (staying sharp on algorithms/interviews)

## Open Tasks

### Raffaella
- [ ] Verify order notification emails/WhatsApp actually arrive — needs a real Gmail App Password + Twilio SID/token filled into `application.properties`; code is done and safely no-ops when blank, but nothing has been sent for real yet
- [ ] Build an admin UI to moderate/delete product reviews (reviews themselves are done, no moderation surface)
- [ ] Wire real image upload on the frontend product form (backend already ready)
- [ ] Add password-change controller endpoint (DTO already exists)
- [ ] Decide: re-enable Stripe (clearly labeled USD) or wait for Konnect — Stripe was pulled from the checkout UI when prices switched to TND display, since Stripe can't charge in TND
- [ ] Manually test the promo banner admin flow in a browser (builds clean, untested end-to-end)
- [ ] Wishlist, detailed order status history (BESOINS_ET_SUGGESTIONS.md wave 2)
- [ ] Add Docker/docker-compose, CI/CD pipeline, `.env.example`

### OnBoarding
- [ ] Let a module proposer edit their own module after submission (BESOINS.md §1.2) — currently TEAM_LEAD/ADMIN only
- [ ] Migrate frontend `pages/` → `features/` (documented in ARCHITECTURE.md §3.3, not started)
- [ ] Check the `announcement/` context for the same lazy-proxy/`open-in-view=false` bug that was fixed in `community/`

### Bonplan
- [ ] Étape 2: Catalogue & recherche IA (SearchController + AnthropicService)
- [ ] Étape 3: Page établissement (galerie, menu, deals, avis)
- [ ] Étape 4: Réservation (formulaire, email Kafka, profil)
- [ ] Étape 5: Carte Mapbox
- [ ] Étape 6: Back-office admin

## Session Protocol

- Treat this file as continuity memory across sessions and machines: when starting fresh, assume the state below is the latest known state of Yahia's work.
- As work happens — status changes, tasks finished or added, new projects/ideas — update this file directly, au fur et à mesure. Don't wait until the end of a session or until asked.
- After updating, push the change straight to `github.com/YahiaSnoussi/portfolio` (branch `main`) — small incremental commits as things change, not one batched push at the end.
- Keep the local memory system (project/feedback/reference memories) refreshed and organized in step with this file, same trigger, same cadence.

## Update Log

- 2026-08-19: Initial version created (background, Raffaella, OnBoarding, learning goals).
- 2026-08-19: Added Bonplan project, linked related skills (`/onboarding`, `/onboarding-details`, `/raffaella-brand`), added per-project open-task backlog, switched to an always-update-and-push session protocol.
- 2026-08-20: Raffaella session — shipped coupons, verified-purchase product reviews, order email/WhatsApp notifications (untested with real credentials), TND currency display, add-whole-package-to-cart, admin nav-section visibility controls, category images + mega-menu hover preview, admin sidebar rework, and a notification-bell timezone bug fix. Refreshed the Raffaella open-task backlog accordingly; `/raffaella-brand` and the project's own `ARCHITECTURE.md`/`BESOINS_ET_SUGGESTIONS.md` were updated in the same session.
- 2026-08-21: Raffaella session — fixed carousel/cart images not rendering (unquoted CSS `url()` broke on filenames with spaces/apostrophes; fixed by quoting+encoding, plus sanitizing filenames at upload time going forward), fixed Google Sign-In being slow (was rebuilding a `GoogleIdTokenVerifier` + re-fetching Google's certs on every login instead of reusing one), added scroll-to-form UX on the Categories/Coupons/Carousel-slide admin edit screens, and added a 7 TND Cash-on-Delivery fee (server-computed, mirrors the coupon pattern of never trusting the frontend's number). The user separately fixed the "Erreur 401: invalid_client" Google Sign-In issue on the Google Cloud Console side, confirming it was never an app-code problem. **Also: committed and pushed the entire accumulated Raffaella feature set to `origin/master` for the first time** (commit `5bef56b`) — it had been sitting uncommitted in the working tree since 2026-07-30.
