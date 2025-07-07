# 🔥 POPRAWIONE ALERTY - BlazeKillTracker

## ✅ Co zostało naprawione:

### 1. **Problem z alertami przy trzymaniu jajka**

- ❌ **Poprzednio**: Alerty pojawiały się już przy trzymaniu spawn egg
- ✅ **Teraz**: Alerty pojawiają się **TYLKO** przy rzeczywistym zespawnowaniu moba

### 2. **Dodano komendę `/blazekill hist <gracz>`**

- ✅ **Nowa funkcja**: Historia respawnów dla każdego gracza
- ✅ **Szczegóły**: Pokazuje miejsce, czas i typ moba (Blaze/Ghast)
- ✅ **Panel**: Czytelny format z podziałem na sekcje

---

## 🎮 Nowe funkcje:

### `/blazekill hist <gracz>`

Pokazuje pełną historię respawnów gracza:

- **Typ moba**: Blaze lub Ghast
- **Czas**: Dokładna data i godzina
- **Świat**: W którym świecie
- **Lokalizacja**: Współrzędne (x, y, z)
- **Statystyki**: Łączna liczba respawnów

### Przykład wyniku:

```
=== Historia respawnów dla Player123 ===
Mob: Blaze
Czas: 07-07-2025 14:30:25
Świat: world_nether
Lokalizacja: 123, 64, 456
---
Mob: Ghast
Czas: 07-07-2025 15:45:12
Świat: world_nether
Lokalizacja: 200, 100, 300
---
Łącznie respawnów: 2
```

---

## 🎯 Wszystkie komendy:

- `/blazekill active` - Włącza alerty o spawn eggs
- `/blazekill deactive` - Wyłącza alerty o spawn eggs
- `/blazekill hist <gracz>` - **NOWE!** Historia respawnów gracza
- `/blazekill help` - Wyświetla pomoc

---

## 🔧 Jak teraz działają alerty:

1. **Gracz używa spawn egg** - Plugin jeszcze nie wysyła alertu
2. **Mob się rzeczywiście spawuje** - Dopiero teraz wysyłany jest alert
3. **Alert zawiera**: Kto, co, gdzie i kiedy zespawnował
4. **Klikalne tp**: Kliknij w alert aby się steleportować

### Przykład alertu:

```
[SPAWN ALERT] Player123 zespawnował Blaze w world_nether (123, 64, 456) - Kliknij aby się tp!
```

---

## 📁 Plik do instalacji:

**`blazekilltracker-1.0.jar`** - Najnowsza wersja z poprawkami

Znajdziesz go w: `c:\Users\igorf\Desktop\PLuginymc\SpawnMobInfoPlugin\target\blazekilltracker-1.0.jar`

---

## 🚀 Instalacja:

1. **Skopiuj** plik `blazekilltracker-1.0.jar` do folderu `plugins` na serwerze
2. **Zrestartuj** serwer
3. **Sprawdź** komendą `/plugins` czy plugin się załadował

---

## 🔍 Tab completion:

- `/blazekill` + **TAB** → pokazuje: `active`, `deactive`, `hist`, `help`
- `/blazekill hist` + **TAB** → pokazuje graczy online
- `/blazekills` + **TAB** → pokazuje graczy online

---

## 📝 Zapisywane dane:

Plugin tworzy automatycznie:

- `plugins/BlazeKillTracker/blaze_kills.txt` - Zabójstwa Blaze
- `plugins/BlazeKillTracker/spawn_history.txt` - **NOWE!** Historia respawnów
- `plugins/BlazeKillTracker/alerts_config.txt` - Ustawienia alertów

---

## 🎉 Autor: jaruso99

**Wszystkie problemy rozwiązane!**

- ✅ Alerty tylko przy rzeczywistym spawnie
- ✅ Historia respawnów `/blazekill hist <gracz>`
- ✅ Lepsze wykrywanie graczy spawujących moby
- ✅ Zaktualizowany tab completion

**Plugin gotowy do użycia!** 🎮
