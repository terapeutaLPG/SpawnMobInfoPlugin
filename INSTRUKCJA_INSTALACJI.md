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

1. Znajdź plik: `BlazeKillTracker-fixed.jar` w folderze `c:\Users\igorf\Desktop\PLuginymc\`
2. Skopiuj ten plik do folderu `plugins` na twoim serwerze Minecraft

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

- `/blazekills` - Wyświetla ogólne statystyki zabójstw Blaze
- `/blazekills <gracz>` - Wyświetla statystyki konkretnego gracza
- `/blazekillsreload` - Przeładowuje konfigurację pluginu

## Uprawnienia

- `blazekilltracker.view` - Pozwala przeglądać statystyki (domyślnie: wszystkim)
- `blazekilltracker.reload` - Pozwala przeładowywać konfigurację (domyślnie: tylko operatorom)

## Funkcjonalność

- Automatycznie zapisuje informacje o zabójstwach Blaze
- Przechowuje: nazwę gracza, UUID, świat, współrzędne, datę i czas
- Wyświetla ranking najlepszych graczy
- Powiadamia gracza o zapisaniu zabójstwa

## Jeśli plugin nadal nie działa

1. **Sprawdź logi serwera** - szukaj błędów związanych z BlazeKillTracker
2. **Sprawdź wersję serwera** - plugin działa z Spigot/Paper 1.13+
3. **Sprawdź uprawnienia** - upewnij się, że masz dostęp do komend
4. **Sprawdź folder plugins** - plik JAR musi być w odpowiednim miejscu

## Pliki konfiguracyjne

Plugin utworzy automatycznie:

- `plugins/BlazeKillTracker/config.yml` - Konfiguracja
- `plugins/BlazeKillTracker/blaze_kills.txt` - Dane o zabójstwach
