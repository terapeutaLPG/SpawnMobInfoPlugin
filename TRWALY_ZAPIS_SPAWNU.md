# 💾 TRWAŁY ZAPIS SPAWNU MOBÓW - BlazeKillTracker

## ✅ Co zostało naprawione:

### 🔄 **Informacje o spawnie przetrwają restart serwera**

- ✅ **Zapis do pliku**: Informacje o spawnie mobów są zapisywane do `mob_spawn_info.txt`
- ✅ **Automatyczne wczytywanie**: Po restarcie serwera dane są automatycznie wczytywane
- ✅ **Natychmiastowy zapis**: Każdy spawn moba jest od razu zapisywany do pliku
- ✅ **Automatyczne czyszczenie**: Stare wpisy (21+ dni) są automatycznie usuwane

### 🔧 **Jak to działa:**

1. **Spawn moba** → Informacje zapisywane do pamięci + pliku
2. **Restart serwera** → Plugin automatycznie wczytuje dane z pliku
3. **MobLog sprawdza** → Nadal pokazuje kto i kiedy zespawnował moba
4. **Czyszczenie co 21 dni** → Stare wpisy są automatycznie usuwane

---

## 📁 **Nowy plik danych:**

### `plugins/BlazeKillTracker/mob_spawn_info.txt`

**Format:**

```
mobUUID;spawnerName;spawnerUUID;spawnTime;x;y;z
```

**Przykład:**

```
f47ac10b-58cc-4372-a567-0e02b2c3d479;Player123;a1b2c3d4-e5f6-7890-abcd-ef1234567890;07-07-2025 15:30:25;123;64;456
```

---

## 🎮 **Testowanie funkcji:**

### **Test 1: Spawn + restart**

1. Użyj spawn egg na moba
2. Sprawdź mobem łopatą MobLog - powinieneś zobaczyć dane
3. Zrestartuj serwer
4. Sprawdź ponownie łopatą MobLog - dane powinny nadal być!

### **Test 2: Sprawdzenie pliku**

1. Otwórz `plugins/BlazeKillTracker/mob_spawn_info.txt`
2. Powinieneś zobaczyć wpisy o mobch które zespawnowałeś

### **Test 3: Automatyczne czyszczenie**

- Wpisy starsze niż 21 dni są automatycznie usuwane
- W konsoli zobaczysz: `Cleaned up X old mob spawn info records`

---

## 🔄 **Cykl życia danych:**

### **Przy spawnie moba:**

1. Gracz używa spawn egg
2. Plugin zapisuje informacje do HashMap (pamięć)
3. Plugin natychmiast zapisuje do pliku `mob_spawn_info.txt`

### **Przy restarcie serwera:**

1. Plugin startuje
2. Automatycznie wczytuje dane z `mob_spawn_info.txt`
3. HashMap jest odtwarzana z danymi sprzed restartu

### **Przy używaniu MobLog:**

1. Gracz klika łopatą na moba
2. Plugin sprawdza UUID moba w HashMap
3. Pokazuje informacje o spawnerze (nawet po restarcie!)

### **Automatyczne czyszczenie (co 24h):**

1. Plugin sprawdza wiek wszystkich wpisów
2. Usuwa wpisy starsze niż 21 dni z HashMap
3. Przepisuje plik z oczyszczonymi danymi

---

## 📊 **Statystyki w konsoli:**

### **Przy starcie pluginu:**

```
[INFO] BlazeKillTracker has been enabled!
```

_(Dane automatycznie wczytane z pliku)_

### **Przy czyszczeniu:**

```
[INFO] Log cleanup completed - removed entries older than 21 days
[INFO] Cleaned up 5 old blaze kill records
[INFO] Cleaned up 3 old spawn history records
[INFO] Cleaned up 12 old mob spawn info records
```

### **Przy wyłączeniu:**

```
[INFO] BlazeKillTracker has been disabled!
```

_(Dane automatycznie zapisane do pliku)_

---

## 📁 **Wszystkie pliki danych (zaktualizowane):**

Plugin automatycznie tworzy i zarządza:

- `plugins/BlazeKillTracker/blaze_kills.txt` - Zabójstwa Blaze (21 dni)
- `plugins/BlazeKillTracker/spawn_history.txt` - Historia respawnów (21 dni)
- `plugins/BlazeKillTracker/mob_spawn_info.txt` - **NOWE!** Informacje o spawnie mobów (21 dni)
- `plugins/BlazeKillTracker/alerts_config.txt` - Ustawienia alertów
- `plugins/BlazeKillTracker/config.yml` - Konfiguracja pluginu

---

## 🎯 **Przykład użycia:**

### **Scenariusz: Sprawdzenie moba po restarcie**

1. **Przed restartem:**

   ```
   Gracz używa Blaze spawn egg
   MobLog pokazuje: "Zespawnowany przez Player123"
   ```

2. **Restart serwera**

   ```
   Serwer się restartuje
   Plugin wczytuje dane z mob_spawn_info.txt
   ```

3. **Po restarcie:**
   ```
   MobLog nadal pokazuje: "Zespawnowany przez Player123"
   Data i czas spawnu są zachowane!
   ```

---

## 🔧 **Zalety nowego systemu:**

### **Trwałość danych**

- ✅ Informacje przetrwają restart serwera
- ✅ Backup danych w pliku tekstowym
- ✅ Możliwość ręcznej edycji/przeglądu

### **Automatyczne zarządzanie**

- ✅ Natychmiastowy zapis przy spawnie
- ✅ Automatyczne wczytywanie przy starcie
- ✅ Regularne czyszczenie starych danych

### **Wydajność**

- ✅ Szybki dostęp z HashMap w pamięci
- ✅ Zapis tylko przy zmianach
- ✅ Automatyczne zarządzanie rozmiarem plików

---

## 📁 **Plik do instalacji:**

**`blazekilltracker-1.0.jar`** - Najnowsza wersja z trwałym zapisem spawnu

Znajdziesz go w: `c:\Users\igorf\Desktop\PLuginymc\SpawnMobInfoPlugin\target\blazekilltracker-1.0.jar`

---

## 🚀 **Instalacja:**

1. **Skopiuj** plik `blazekilltracker-1.0.jar` do folderu `plugins` na serwerze
2. **Zrestartuj** serwer
3. **Sprawdź** komendą `/plugins` czy plugin się załadował
4. **Testuj** - zespawnuj moba, zrestartuj serwer, sprawdź łopatą MobLog

---

## 🎉 **Autor: jaruso99**

**Problem rozwiązany! MobLog działa nawet po restarcie serwera!**

- ✅ Trwały zapis informacji o spawnie mobów
- ✅ Automatyczne wczytywanie po restarcie
- ✅ Regularne czyszczenie starych danych (21 dni)
- ✅ Natychmiastowy zapis przy każdym spawnie
- ✅ Pełna kompatybilność z istniejącymi funkcjami

**Teraz możesz sprawdzać kto zespawnował moba nawet tygodnie później!** 🎮
