# 🚀 Instrukcja instalacji BlazeKillTracker

## ⚠️ WAŻNE - Naprawiony problem z plugin.yml

Problem został naprawiony! Plugin.yml jest teraz w poprawnym miejscu w JARze i plugin powinien się załadować.

## Kroki instalacji:

### 1. Zbuduj plugin

```bash
cd "C:\Users\igorf\Desktop\PLuginymc\SpawnMobInfoPlugin"
mvn clean package
```

### 2. Znajdź plik JAR

Po zbudowaniu znajdziesz plik `blazekilltracker-1.0.jar` w folderze:

```
target/blazekilltracker-1.0.jar
```

### 3. Zainstaluj na serwerze

1. **Skopiuj plik JAR** (nie folder!) do folderu `plugins` na twoim serwerze
2. **Zrestartuj serwer** lub użyj komendy `/reload`

### 4. Sprawdź czy działa

- Użyj komendy `/pl` - powinieneś zobaczyć `BlazeKillTracker` na liście pluginów
- Użyj komendy `/blazekills` aby sprawdzić czy plugin działa
- Zabij Blaze w grze - powinieneś zobaczyć wiadomość o zapisaniu zabójstwa

## ✅ Sprawdź czy plugin się załadował:

```
/pl
```

Na liście powinieneś zobaczyć: `BlazeKillTracker` (zielony = włączony)

## 📊 Przykład użycia:

### Przeglądanie statystyk ogólnych:

```
/blazekills
```

Wyświetli:

- Całkowitą liczbę zabójstw Blaze
- Ranking najlepszych graczy

### Sprawdzanie statystyk gracza:

```
/blazekills Steve
```

Wyświetli szczegółowe informacje o wszystkich zabójstwach gracza Steve

### Przeładowanie pluginu:

```
/blazekillsreload
```

## 📁 Gdzie są zapisane dane:

Plugin zapisuje dane w pliku:

```
plugins/BlazeKillTracker/blaze_kills.txt
```

Format danych:

```
nick_gracza;uuid_gracza;świat;x;y;z;data_czas
```

## 🎯 Co rejestruje plugin:

Przy każdym zabiciu Blaze przez gracza zapisuje:

- **Nick gracza** - kto zabił
- **UUID gracza** - unikalny identyfikator
- **Świat** - w którym świecie
- **Lokalizacja** - dokładne współrzędne (x, y, z)
- **Czas** - dokładna data i godzina

## ⚙️ Konfiguracja:

Możesz edytować plik `config.yml` w folderze pluginu aby dostosować:

- Format powiadomień
- Format daty i czasu
- Liczbę wyświetlanych graczy w rankingu

**Plugin jest gotowy do użycia!** 🎉

## 🔧 Kompatybilność:

- ✅ Paper 1.20.4 (twój serwer)
- ✅ Spigot 1.20+
- ✅ API version 1.20
