# BlazeKillTracker Plugin

Plugin do Minecraft który śledzi kto zabija moby Blaze, zapisując nick gracza, lokalizację i czas.

## Funkcje

- **Automatyczne śledzenie**: Plugin automatycznie rejestruje każde zabicie Blaze przez gracza
- **Szczegółowe informacje**: Zapisuje nick gracza, UUID, świat, współrzędne (x,y,z) i dokładny czas
- **Zapis do pliku**: Wszystkie dane są zapisywane do pliku `blaze_kills.txt` w folderze pluginu
- **Komendy**: Dostępne komendy do przeglądania statystyk

## Komendy

### `/blazekills`

Wyświetla ogólne statystyki zabójstw Blaze

- Pokazuje całkowitą liczbę zabójstw
- Wyświetla ranking najlepszych graczy
- **Uprawnienie**: `blazekilltracker.view`

### `/blazekills <nick_gracza>`

Wyświetla szczegółowe statystyki dla konkretnego gracza

- Pokazuje wszystkie zabójstwa Blaze danego gracza
- Wyświetla czas, świat i lokalizację każdego zabójstwa
- **Uprawnienie**: `blazekilltracker.view`

### `/blazekillsreload`

Przeładowuje konfigurację pluginu

- **Uprawnienie**: `blazekilltracker.reload`

## Uprawnienia

- `blazekilltracker.view` - Pozwala na przeglądanie statystyk (domyślnie: wszyscy gracze)
- `blazekilltracker.reload` - Pozwala na przeładowanie pluginu (domyślnie: operatorzy)

## Instalacja

1. Skompiluj plugin używając Maven: `mvn clean package`
2. Skopiuj wygenerowany plik JAR do folderu `plugins` na serwerze
3. Zrestartuj serwer lub użyj `/reload`

## Format danych

Dane są zapisywane w pliku `blaze_kills.txt` w formacie:

```
nick_gracza;uuid_gracza;świat;x;y;z;data_czas
```

Przykład:

```
Steve;f47ac10b-58cc-4372-a567-0e02b2c3d479;world;123;64;456;06-07-2025 14:30:25
```

## Wymagania

- Minecraft 1.13+
- Spigot/Paper server
- Java 8+

## Budowanie

Użyj Maven do zbudowania pluginu:

```bash
mvn clean package
```

Wygenerowany plik JAR znajdziesz w folderze `target/`.
