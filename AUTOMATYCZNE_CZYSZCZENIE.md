# 🆕 NOWE FUNKCJE - BlazeKillTracker

## ✅ Co zostało dodane:

### 🧹 **Automatyczne czyszczenie logów (21 dni)**

- ✅ **Automatyczne uruchamianie**: Co 24 godziny sprawdza stare logi
- ✅ **Usuwanie po 21 dniach**: Automatycznie usuwa wpisy starsze niż 21 dni
- ✅ **Dwa typy logów**: Czyści zarówno zabójstwa Blaze jak i historię spawnu
- ✅ **Informacje w konsoli**: Loguje liczbę usuniętych wpisów

### 🚀 **Komenda `/blazekill tp <gracz>` - Teleportacja**

- ✅ **Teleportacja do ostatniego spawnu**: Przenosi do ostatniego miejsca spawnu gracza
- ✅ **Szczegółowe informacje**: Pokazuje typ moba, czas i lokalizację
- ✅ **Bezpieczeństwo**: Sprawdza czy świat istnieje przed teleportacją
- ✅ **Precyzyjne pozycjonowanie**: Teleportuje na środek bloku

---

## 🎮 Jak używać nowych funkcji:

### 1. **Automatyczne czyszczenie logów**

**Działa w tle automatycznie!**

- Uruchamia się co 24 godziny
- Usuwa wpisy starsze niż 21 dni
- Nie wymaga interwencji administratora

### 2. **Komenda teleportacji**

```
/blazekill tp <gracz>
```

**Przykład:**

```
/blazekill tp Player123
```

**Wynik:**

```
Teleportowano do ostatniego spawnu gracza Player123
Mob: Blaze
Czas: 07-07-2025 15:30:25
Lokalizacja: world_nether (123, 64, 456)
```

---

## 🔧 Techniczne szczegóły:

### **Automatyczne czyszczenie**

- **Częstotliwość**: Co 24 godziny
- **Okres przechowywania**: 21 dni
- **Pliki**: `blaze_kills.txt` + `spawn_history.txt`
- **Logowanie**: Informacje w konsoli serwera

### **Komenda teleportacji**

- **Uprawnienie**: `blazekilltracker.alerts`
- **Wyszukiwanie**: Znajduje najnowszy spawn gracza
- **Teleportacja**: Na środek bloku (X+0.5, Y, Z+0.5)
- **Sprawdzanie**: Czy świat istnieje

---

## 🎯 Wszystkie komendy (zaktualizowane):

- `/blazekill active` - Włącza alerty o spawn eggs
- `/blazekill deactive` - Wyłącza alerty o spawn eggs
- `/blazekill hist <gracz>` - Historia respawnów gracza
- `/blazekill logitem` - Daje łopatę MobLog
- `/blazekill lastspawn` - Ostatnich 4 graczy którzy zespawnowali moby
- `/blazekill tp <gracz>` - **NOWE!** Teleportuje do ostatniego spawnu gracza
- `/blazekill help` - Wyświetla pomoc

---

## 🔍 Tab completion (zaktualizowane):

- `/blazekill` + **TAB** → pokazuje: `active`, `deactive`, `hist`, `logitem`, `lastspawn`, `tp`, `help`
- `/blazekill hist` + **TAB** → pokazuje graczy online
- `/blazekill tp` + **TAB** → pokazuje graczy online (**NOWE!**)
- `/blazekills` + **TAB** → pokazuje graczy online

---

## 📊 Przykłady użycia:

### **Sprawdzenie ostatniego spawnu i teleportacja**

```
/blazekill hist Player123
```

_(Sprawdza historię gracza)_

```
/blazekill tp Player123
```

_(Teleportuje do ostatniego spawnu gracza)_

### **Czyszczenie logów (automatyczne)**

```
[INFO] Log cleanup completed - removed entries older than 21 days
[INFO] Cleaned up 15 old blaze kill records
[INFO] Cleaned up 8 old spawn history records
```

---

## 🚨 Komunikaty błędów:

### **Teleportacja**

```
Nie znaleziono żadnych respawnów gracza Player123
```

_(Gracz nie ma historii respawnów)_

```
Świat world_nether nie istnieje!
```

_(Świat został usunięty)_

```
Użycie: /blazekill tp <gracz>
```

_(Błędne użycie komendy)_

---

## 📁 Plik do instalacji:

**`blazekilltracker-1.0.jar`** - Najnowsza wersja z automatycznym czyszczeniem i teleportacją

Znajdziesz go w: `c:\Users\igorf\Desktop\PLuginymc\SpawnMobInfoPlugin\target\blazekilltracker-1.0.jar`

---

## 🚀 Instalacja:

1. **Skopiuj** plik `blazekilltracker-1.0.jar` do folderu `plugins` na serwerze
2. **Zrestartuj** serwer
3. **Sprawdź** komendą `/plugins` czy plugin się załadował
4. **Testuj** komendą `/blazekill tp <gracz>`

---

## 📝 Zapisywane dane (po czyszczeniu):

Plugin automatycznie utrzymuje:

- `plugins/BlazeKillTracker/blaze_kills.txt` - Ostatnie 21 dni zabójstw
- `plugins/BlazeKillTracker/spawn_history.txt` - Ostatnie 21 dni respawnów
- `plugins/BlazeKillTracker/alerts_config.txt` - Ustawienia alertów

---

## 🎉 Autor: jaruso99

**Nowe funkcje automatyzują zarządzanie pluginem!**

- ✅ Automatyczne czyszczenie logów co 24h
- ✅ Usuwanie danych starszych niż 21 dni
- ✅ Teleportacja do ostatniego spawnu gracza
- ✅ Lepsze zarządzanie przestrzenią dyskową
- ✅ Zaktualizowane tab completion i pomoc

**Idealne do długoterminowego użytkowania na serwerze!** 🎮
