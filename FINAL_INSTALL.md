🔥 **PROBLEM NAPRAWIONY! Plugin BlazeKillTracker jest gotowy**

## ✅ Co zostało naprawione:

1. **plugin.yml** - Przeniesiony do `src/main/resources/plugin.yml` (był w złym miejscu)
2. **API Version** - Zaktualizowany z 1.13 na 1.20 (zgodny z twoim serwerem Paper 1.20.4)
3. **Spigot API** - Zaktualizowany do wersji 1.20.4-R0.1-SNAPSHOT
4. **JAR** - Przebudowany z poprawną strukturą

## 🚀 Instalacja:

1. **Skopiuj plik JAR** z `target/blazekilltracker-1.0.jar` do folderu `plugins` na serwerze
2. **Zrestartuj serwer** lub użyj `/reload`
3. **Sprawdź** czy plugin się załadował: `/pl` (powinieneś zobaczyć `BlazeKillTracker`)

## 🎯 Testowanie:

- Użyj `/blazekills` - powinieneś zobaczyć wiadomość o statystykach
- Zabij Blaze w grze - powinieneś zobaczyć powiadomienie o zapisaniu
- Sprawdź folder `plugins/BlazeKillTracker/` - powinien się utworzyć plik `blaze_kills.txt`

## 🔧 Komendy:

- `/blazekills` - Ogólne statystyki
- `/blazekills <nick>` - Statystyki gracza
- `/blazekillsreload` - Przeładuj plugin

**Plugin jest teraz w pełni kompatybilny z Paper 1.20.4!** 🎉

Jeśli nadal będą problemy, pokaż mi logi z uruchomienia serwera.
