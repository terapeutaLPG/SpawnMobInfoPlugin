# 🔥 BlazeKillTracker - Plugin by jaruso99

## ✅ FINALNA WERSJA GOTOWA!

### 🎯 Funkcje pluginu:

- **Śledzenie zabójstw Blaze** - Zapisuje kto, gdzie i kiedy zabił Blaze
- **Alerty o spawn eggs** - Powiadamia operatorów o użyciu Blaze/Ghast spawn eggs
- **Klikalne teleportacje** - Kliknij w alert aby się steleportować
- **Tab completion** - Autocompletowanie komend
- **Kompletny system pomocy** - `/blazekill help`

### 🎮 Monitorowane moby:

- **Blaze** - Rejestruje zabójstwa + alerty o spawn eggs
- **Ghast** - Alerty o spawn eggs

### 📋 Plik do instalacji:

**`BlazeKillTracker-Final-jaruso99.jar`** - Finalna wersja

---

## 🚀 Instalacja:

### Krok 1: Skopiuj plik

1. Znajdź plik: `BlazeKillTracker-Final-jaruso99.jar`
2. Skopiuj go do folderu `plugins` na serwerze
3. Zrestartuj serwer

### Krok 2: Sprawdź działanie

- `/plugins` - Sprawdź czy plugin się załadował
- `/blazekill help` - Sprawdź komendy

---

## 🎯 Komendy:

### Podstawowe:

- `/blazekills` - Ogólne statystyki zabójstw Blaze
- `/blazekills <gracz>` - Statystyki konkretnego gracza
- `/blazekillsreload` - Przeładuj plugin

### Alerty spawn eggs:

- `/blazekill active` - **Włącza** alerty o Blaze/Ghast spawn eggs
- `/blazekill deactive` - **Wyłącza** alerty
- `/blazekill help` - Wyświetla pomoc

### 🔧 Tab completion:

- Naciśnij **TAB** po `/blazekill` → pokaże opcje (active, deactive, help)
- Naciśnij **TAB** po `/blazekills` → pokaże graczy online

---

## 🔐 Uprawnienia:

- `blazekilltracker.view` - Przeglądanie statystyk (domyślnie: wszyscy)
- `blazekilltracker.reload` - Przeładowanie pluginu (domyślnie: operatorzy)
- `blazekilltracker.alerts` - Zarządzanie alertami (domyślnie: operatorzy)

---

## 🎮 Jak to działa:

### Zabójstwa Blaze:

1. Gracz zabija Blaze
2. Plugin zapisuje: nick, UUID, lokalizację, czas
3. Gracz dostaje powiadomienie o zapisaniu

### Alerty spawn eggs:

1. Gracz używa Blaze/Ghast spawn egg
2. Wszyscy operatorzy z włączonymi alertami dostają powiadomienie
3. Kliknięcie w powiadomienie teleportuje do lokacji

### Przykład alertu:

```
[SPAWN ALERT] Player123 zespawnował Blaze w world (100, 64, 200) - Kliknij aby się tp!
```

---

## 📁 Pliki pluginu:

Plugin automatycznie utworzy:

- `plugins/BlazeKillTracker/blaze_kills.txt` - Dane o zabójstwach
- `plugins/BlazeKillTracker/alerts_config.txt` - Ustawienia alertów
- `plugins/BlazeKillTracker/config.yml` - Konfiguracja

---

## 🎉 Autor: jaruso99

Plugin gotowy do użycia! Wszystkie funkcje działają poprawnie.

### Co nowego w tej wersji:

✅ Alerty tylko dla Blaze i Ghast  
✅ Czytelniejsze komunikaty  
✅ Pełny tab completion  
✅ Komenda help  
✅ Klikalne teleportacje  
✅ Automatyczne zapisywanie ustawień

**Miłej zabawy!** 🎮
