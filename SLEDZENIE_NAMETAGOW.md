# 🏷️ ŚLEDZENIE NAMETAGÓW - BlazeKillTracker

## ✅ Co zostało dodane:

### 🏷️ **Kompletne śledzenie nametagów**

- ✅ **Wykrywanie nadawania nametagów**: Plugin automatycznie zapisuje kto i kiedy nadał nametag na moba
- ✅ **Wyświetlanie w MobLog**: Łopata MobLog pokazuje informacje o nametagu oraz o spawnie
- ✅ **Trwały zapis**: Informacje o nametagach przetrwają restart serwera
- ✅ **Automatyczne czyszczenie**: Stare wpisy o nametagach (21+ dni) są automatycznie usuwane

---

## 🎮 Jak działa śledzenie nametagów:

### 1. **Nadawanie nametagu**

```
Gracz używa name tag na mobie → Plugin automatycznie zapisuje:
- Kto nadał nametag (nick gracza)
- Jaki tekst został nadany
- Kiedy dokładnie (data i godzina)
- UUID moba
```

### 2. **Sprawdzanie MobLog**

```
/blazekill logitem
→ Kliknij prawym na moba
→ Zobacz pełne informacje:
  ✅ Kto zespawnował moba
  ✅ Kiedy zespawnował
  ✅ Gdzie zespawnował
  ✅ Kto nadał nametag
  ✅ Jaki tekst nametagu
  ✅ Kiedy nadał nametag
```

### 3. **Automatyczne zarządzanie**

- **Natychmiastowy zapis**: Każde użycie nametagu jest od razu zapisywane
- **Wczytywanie po restarcie**: Dane są automatycznie przywracane
- **Czyszczenie co 24h**: Wpisy starsze niż 21 dni są usuwane

---

## 🎯 Przykład działania:

### **Scenariusz:**

1. **Gracz A** używa spawn egg → Spawnje Blaze
2. **Gracz B** nadaje nametag "Boss Nether" na tego Blaze
3. **Gracz C** używa MobLog na tego Blaze

### **Wynik w chacie:**

```
=== MobLog Info ===
Typ moba: BLAZE
Zespawnowany przez: GraczA
Czas spawnu: 09-07-2025 18:15:30
Lokalizacja spawnu: 123, 64, 456

--- Nametag Info ---
Nametag nadany przez: GraczB
Tekst nametag'a: "Boss Nether"
Czas nadania: 09-07-2025 18:20:15
```

---

## 🔧 Techniczne szczegóły:

### **Śledzenie nametagów**

- **Event**: `PlayerInteractEntityEvent` z name tag w ręce
- **Zapis**: `mob_nametags.txt` (UUID;gracz|uuid|tekst|czas)
- **Pamięć**: `HashMap<UUID, NametagInfo>` dla szybkiego dostępu
- **Czyszczenie**: Automatyczne co 24h (wpisy 21+ dni)

### **Klasa NametagInfo**

```java
- giverName: String (nick gracza)
- giverUuid: String (UUID gracza)
- nametagText: String (tekst nametagu)
- giveTime: String (czas nadania)
```

### **Integracja z MobLog**

- Sprawdza HashMap mobNametags
- Wyświetla info razem z danymi spawnu
- Działa nawet jeśli brak danych spawnu
- Pokazuje też dla mobów spawnowanych naturalnie

---

## 📁 **Wszystkie pliki danych (kompletne):**

Plugin automatycznie tworzy i zarządza:

- `plugins/BlazeKillTracker/blaze_kills.txt` - Zabójstwa Blaze (21 dni)
- `plugins/BlazeKillTracker/spawn_history.txt` - Historia respawnów (21 dni)
- `plugins/BlazeKillTracker/mob_spawn_info.txt` - Informacje o spawnie mobów (21 dni)
- `plugins/BlazeKillTracker/mob_nametags.txt` - **NOWE!** Informacje o nametagach (21 dni)
- `plugins/BlazeKillTracker/alerts_config.txt` - Ustawienia alertów
- `plugins/BlazeKillTracker/config.yml` - Konfiguracja pluginu

---

## 🎯 Wszystkie komendy (finalne):

- `/blazekill active` - Włącza alerty o spawn eggs
- `/blazekill deactive` - Wyłącza alerty o spawn eggs
- `/blazekill hist <gracz>` - Historia respawnów gracza
- `/blazekill logitem` - Daje łopatę MobLog (pokazuje spawny + nametagi)
- `/blazekill lastspawn` - Ostatnich 4 graczy którzy zespawnowali moby
- `/blazekill tp <gracz>` - Teleportuje do ostatniego spawnu gracza
- `/blazekill help` - Wyświetla pomoc

---

## 🔍 Tab completion (finalne):

```
/blazekill [TAB] → active, deactive, hist, logitem, lastspawn, tp, help
/blazekill hist [TAB] → Lista graczy online
/blazekill tp [TAB] → Lista graczy online
```

---

## 📊 Przykłady użycia nametagów:

### **Test 1: Podstawowe użycie**

1. Użyj spawn egg na moba
2. Nadaj nametag na tego moba
3. Sprawdź łopatą MobLog - powinieneś zobaczyć obie informacje

### **Test 2: Po restarcie**

1. Nadaj nametag na moba
2. Zrestartuj serwer
3. Sprawdź łopatą MobLog - informacje o nametagu powinny nadal być!

### **Test 3: Mob bez spawnu**

1. Znajdź naturalnie spawnego moba
2. Nadaj mu nametag
3. Sprawdź łopatą MobLog - powinieneś zobaczyć info o nametagu mimo braku danych spawnu

---

## 🚨 Komunikaty:

### **Przy sprawdzaniu MobLog**

```
=== MobLog Info ===
Typ moba: BLAZE
Zespawnowany przez: Player123
Czas spawnu: 09-07-2025 18:15:30
Lokalizacja spawnu: 123, 64, 456
--- Nametag Info ---
Nametag nadany przez: Player456
Tekst nametag'a: "Boss"
Czas nadania: 09-07-2025 18:20:15
```

```
Brak informacji o spawnie tego moba
Mob mógł zostać zespawnowany naturalnie lub przed uruchomieniem pluginu
--- Nametag Info ---
Nametag nadany przez: Player789
Tekst nametag'a: "My Pet"
Czas nadania: 09-07-2025 18:25:30
```

```
Brak informacji o nametag'u
```

_(Gdy mob nie ma nametagu)_

---

## 📁 Plik do instalacji:

**`blazekilltracker-1.0.jar`** - Finalna wersja ze śledzeniem nametagów

Znajdziesz go w: `c:\Users\igorf\Desktop\PLuginymc\SpawnMobInfoPlugin\target\blazekilltracker-1.0.jar`

---

## 🚀 Instalacja:

1. **Skopiuj** plik `blazekilltracker-1.0.jar` do folderu `plugins` na serwerze
2. **Zrestartuj** serwer
3. **Sprawdź** komendą `/plugins` czy plugin się załadował
4. **Testuj** komendą `/blazekill logitem`

---

## 🎮 Testowanie nametagów:

### **Test pełnego cyklu:**

1. `/blazekill logitem` - Weź łopatę
2. Użyj spawn egg na Blaze
3. Sprawdź łopatą - powinieneś zobaczyć info o spawnie
4. Nadaj nametag "Test" na tego Blaze
5. Sprawdź łopatą ponownie - powinieneś zobaczyć spawny + nametag

### **Test po restarcie:**

1. Nadaj nametag na moba
2. `/stop` - Zatrzymaj serwer
3. Uruchom serwer ponownie
4. Sprawdź łopatą - info o nametagu powinno nadal być!

---

## 📝 Zapisywane dane (pełne):

Plugin automatycznie utrzymuje przez 21 dni:

- Zabójstwa Blaze z informacjami o zabójcy
- Historia spawnu mobów przez spawn eggs
- Trwałe informacje o spawnie dla MobLog
- **NOWE:** Kompletne informacje o nametagach
- Ustawienia alertów graczy

---

## 🎉 Autor: jaruso99

**FINALNA WERSJA BlazeKillTracker - Kompletny system monitorowania!**

- ✅ Śledzenie spawnu mobów (spawn eggs)
- ✅ Śledzenie zabójstw Blaze
- ✅ Kompletne informacje MobLog (spawny + nametagi)
- ✅ Historia i statystyki graczy
- ✅ Automatyczne zarządzanie danymi (21 dni)
- ✅ Teleportacja do ostatnich spawnów
- ✅ Trwały zapis - wszystko przetrwa restart serwera

**Idealny plugin do pełnego monitorowania aktywności mobów na serwerze!** 🎮
