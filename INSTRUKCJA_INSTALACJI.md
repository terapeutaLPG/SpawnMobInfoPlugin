<<<<<<< HEAD

## NAPRAWIONE KOMENDY! 🎉

### ✅ Co zostało naprawione:

- **Tab completion** - Komendy się teraz autouzupełniają
- **Komenda help** - Dodano `/blazekill help`
- **Lepsze komunikaty** - Czytelniejsze wiadomości w grze
- **Stabilność** - Poprawiono błędy w kodzie

### 🎯 Nowe komendy:

- `/blazekill active` - Włącza alerty o spawn eggs
- `/blazekill deactive` - Wyłącza alerty o spawn eggs
- `/blazekill help` - Wyświetla pomoc

### 🔧 Tab completion:

- Naciśnij **TAB** po `/blazekill` aby zobaczyć opcje
- Naciśnij **TAB** po `/blazekills` aby zobaczyć graczy online

### 📋 Nowy plik do instalacji:

`BlazeKillTracker-FIXED.jar` - Najnowsza wersja z poprawionymi komendami

---

=======

> > > > > > > f686674729b8df65d431a2422e75d9853d6b4f49

# NOWE FUNKCJE! 🎉

### Wykrywanie spawn eggs

Plugin teraz wykrywa gdy gracze używają spawn eggs i powiadamia o tym operatorów!

### Nowe komendy:

- `/blazekill active` - Włącza alerty o spawn eggs
- `/blazekill deactive` - Wyłącza alerty o spawn eggs

### Klikalne powiadomienia

Gdy ktoś używa spawn egg, operatorzy otrzymują klikalne powiadomienie - kliknij aby się steleportować!

### Automatyczne zapisywanie

Ustawienia alertów są automatycznie zapisywane przy wyjściu z serwera.

---

# Instrukcja Instalacji BlazeKillTracker

## Problem z niewidocznym pluginem - rozwiązanie

Plugin został naprawiony! Główne problemy i ich rozwiązania:

### 1. Plugin nie był zbudowany

✅ **ROZWIĄZANE** - Plugin został pomyślnie skompilowany

### 2. Niekompatybilna wersja API

✅ **ROZWIĄZANE** - Zmieniono api-version z 1.20 na 1.13 dla lepszej kompatybilności

### 3. Plik JAR nie był w odpowiednim miejscu

✅ **ROZWIĄZANE** - Utworzono plik JAR gotowy do instalacji

## Instalacja na serwerze

### Krok 1: Skopiuj plik JAR

<<<<<<< HEAD

1. # Znajdź plik: `BlazeKillTracker-FIXED.jar` w folderze `c:\Users\igorf\Desktop\PLuginymc\`
1. Znajdź plik: `BlazeKillTracker-NEW.jar` w folderze `c:\Users\igorf\Desktop\PLuginymc\`
   > > > > > > > f686674729b8df65d431a2422e75d9853d6b4f49
1. Skopiuj ten plik do folderu `plugins` na twoim serwerze Minecraft

### Krok 2: Zrestartuj serwer

1. Zatrzymaj serwer
2. Uruchom serwer ponownie
3. Sprawdź logi serwera - powinieneś zobaczyć:
   ```
   [INFO] [BlazeKillTracker] BlazeKillTracker has been enabled!
   ```

### Krok 3: Sprawdź czy plugin działa

1. Wejdź na serwer
2. Wpisz komendę: `/plugins`
3. Powinieneś zobaczyć "BlazeKillTracker" na liście
4. Sprawdź komendę: `/blazekills`

## Komendy pluginu

### Podstawowe komendy:

- `/blazekills` - Wyświetla ogólne statystyki zabójstw Blaze
- `/blazekills <gracz>` - Wyświetla statystyki konkretnego gracza
- `/blazekillsreload` - Przeładowuje konfigurację pluginu

### NOWE! Komendy alertów:

- `/blazekill active` - Włącza alerty o spawn eggs
  <<<<<<< HEAD
- `/blazekill deactive` - Wyłącza alerty o spawn eggs
- `/blazekill help` - Wyświetla pomoc o komendach

### 🔧 Tab completion:

- Naciśnij **TAB** po wpisaniu `/blazekill` aby zobaczyć dostępne opcje
- # Naciśnij **TAB** po wpisaniu `/blazekills` aby zobaczyć graczy online
- `/blazekill deactive` - Wyłącza alerty o spawn eggs
  > > > > > > > f686674729b8df65d431a2422e75d9853d6b4f49

## Uprawnienia

- `blazekilltracker.view` - Pozwala przeglądać statystyki (domyślnie: wszystkim)
- `blazekilltracker.reload` - Pozwala przeładować konfigurację (domyślnie: operatorom)
- `blazekilltracker.alerts` - Pozwala zarządzać alertami (domyślnie: operatorom)

## Jak działają nowe funkcje:

1. **Wykrywanie spawn eggs**: Gdy gracz używa spawn egg, wszyscy operatorzy z włączonymi alertami otrzymują powiadomienie
2. **Klikalne powiadomienia**: Kliknij na wiadomość w chacie aby się steleportować do miejsca spawnu
3. **Automatyczne zapisywanie**: Ustawienia alertów są zapisywane automatycznie

---

## Jeśli plugin nadal nie działa

1. **Sprawdź logi serwera** - szukaj błędów związanych z BlazeKillTracker
2. **Sprawdź wersję serwera** - plugin działa z Spigot/Paper 1.13+
3. **Sprawdź uprawnienia** - upewnij się, że masz dostęp do komend
4. **Sprawdź folder plugins** - plik JAR musi być w odpowiednim miejscu

## Pliki konfiguracyjne

Plugin utworzy automatycznie:

- `plugins/BlazeKillTracker/config.yml` - Konfiguracja
- `plugins/BlazeKillTracker/blaze_kills.txt` - Dane o zabójstwach
