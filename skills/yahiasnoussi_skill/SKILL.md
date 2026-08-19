---
name: yahiasnoussi_skill
description: Personal context about Yahia Snoussi — background, active projects, open tasks, and learning goals. Load this at the start of a session to pick up where any previous session left off, regardless of machine.
---

# Yahia Snoussi — Personal Context

## Background

- Senior Full-Stack Engineer by day, music producer by night.

## Active Projects

### Raffaella
Premium bio-cosmetics e-commerce brand (Tunisian market + international). Spring Boot + Angular, MySQL. MVP complete.
- Related skill: `/raffaella-brand` — full brand/tech context (stack, architecture, MVP status, deployment gaps).

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
- [ ] Wire real image upload on the frontend product form (backend already ready)
- [ ] Add password-change controller endpoint (DTO already exists)
- [ ] Restore stock on order cancellation (currently only deducted, never restored)
- [ ] Verify Stripe/Konnect payment confirmations server-side (no webhooks yet)
- [ ] Order status email/SMS notifications
- [ ] Resolve Google Sign-In "Erreur 401: invalid_client" — check Google Cloud Console (authorized JS origins / OAuth consent test users), not the app code
- [ ] Manually test the promo banner admin flow in a browser (builds clean, untested end-to-end)
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
