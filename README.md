# BlazeKillTracker Plugin

Plugin do Minecraft który śledzi kto zabija - [SLEDZENIE_NAMETAGOW.md](SLEDZENIE_NAMETAGOW.md) - Dokumentacja śledzenia nametagów

- [SLEDZENIE_BLOKOW.md](SLEDZENIE_BLOKOW.md) - Dokumentacja śledzenia bloków
- [ZABEZPIECZENIE_MOBLOG.md](ZABEZPIECZENIE_MOBLOG.md) - Dokumentacja zabezpieczenia łopaty MobLog
- [UUID_CZAT.md](UUID_CZAT.md) - Dokumentacja funkcji UUID w czaciey Blaze, zapisując nick gracza, lokalizację i czas. Dodatkowo śledzi spawny mobów przez spawn eggi, nametagi oraz zmiany bloków.

## Funkcje

### Podstawowe funkcje

- **Automatyczne śledzenie**: Plugin automatycznie rejestruje każde zabicie Blaze przez gracza
- **Szczegółowe informacje**: Zapisuje nick gracza, UUID, świat, współrzędne (x,y,z) i dokładny czas
- **Zapis do pliku**: Wszystkie dane są zapisywane do pliku `blaze_kills.txt` w folderze pluginu

### Rozszerzone funkcje

- **Śledzenie spawnów**: Monitoruje spawny Blaze i Ghast przez spawn eggi z alertami dla operatorów
- **Historia spawnów**: Zapisuje kto, kiedy i gdzie zespawnował moba
- **Łopata MobLog**: Specjalny item do sprawdzania informacji o spawnie mobów
- **Śledzenie nametagów**: Zapisuje kto i kiedy nadał nametag na moba
- **Śledzenie bloków**: Monitoruje zmiany ważnych bloków (TNT, lawa, skrzynie, itp.)
- **Zaznaczanie terenu**: Możliwość zaznaczania obszaru łopatą MobLog
- **Automatyczne czyszczenie**: Stare logi są automatycznie usuwane (21 dni, bloki 7 dni)
- **Zabezpieczenie MobLog**: Łopata MobLog znika po wyrzuceniu i po restarcie serwera
- **UUID w czacie**: Gracze z uprawnieniem mogą kliknąć nick w czacie aby zobaczyć UUID

## Komendy

### `/blazekill`

Główna komenda pluginu z podkomendami:

- `/blazekill active` - Włącza alerty o spawnach
- `/blazekill deactive` - Wyłącza alerty o spawnach
- `/blazekill hist <gracz>` - Historia spawnów gracza
- `/blazekill logitem` - Otrzymuje łopatę MobLog
- `/blazekill lastspawn` - Ostatni respawnowacze mobów
- `/blazekill tp <gracz>` - Teleport do ostatniego spawnu gracza
- `/blazekill sprawdzbloki` - Sprawdza zmiany bloków w zaznaczonym terenie
- `/blazekill help` - Pomoc

### `/blazekills`

- Wyświetla ogólne statystyki zabójstw Blaze
- `/blazekills <nick_gracza>` - Szczegółowe statystyki gracza

### `/blazekillsreload`

- Przeładowuje konfigurację pluginu

## Uprawnienia

- `blazekilltracker.view` - Przeglądanie statystyk (domyślnie: wszyscy)
- `blazekilltracker.reload` - Przeładowanie pluginu (domyślnie: operatorzy)
- `blazekilltracker.alerts` - Otrzymywanie alertów o spawnach (domyślnie: operatorzy)
- `blazekilltracker.history` - Przeglądanie historii spawnów (domyślnie: wszyscy)
- `blazekilltracker.moblog` - Używanie łopaty MobLog (domyślnie: wszyscy)
- `blazekilltracker.lastspawn` - Sprawdzanie ostatnich spawnów (domyślnie: wszyscy)
- `blazekilltracker.teleport` - Teleportacja do spawnów (domyślnie: operatorzy)
- `blazekilltracker.blockcheck` - Sprawdzanie zmian bloków (domyślnie: wszyscy)
- `blazekilltracker.uidview` - Wyświetlanie UUID gracza w czacie (domyślnie: operatorzy)

## Pliki danych

Plugin tworzy następujące pliki w folderze pluginu:

- `blaze_kills.txt` - Rejestr zabójstw Blaze
- `spawn_history.txt` - Historia spawnów mobów
- `mob_spawn_info.txt` - Informacje o spawnach dla MobLog
- `mob_nametags.txt` - Historia nadawania nametagów
- `block_changes.txt` - Rejestr zmian bloków
- `alerts_config.txt` - Konfiguracja alertów graczy

## Dokumentacja szczegółowa

- [SLEDZENIE_NAMETAGOW.md](SLEDZENIE_NAMETAGOW.md) - Dokumentacja śledzenia nametagów
- [SLEDZENIE_BLOKOW.md](SLEDZENIE_BLOKOW.md) - Dokumentacja śledzenia bloków
- [ZABEZPIECZENIE_MOBLOG.md](ZABEZPIECZENIE_MOBLOG.md) - Dokumentacja zabezpieczenia łopaty MobLog

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
