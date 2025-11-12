# 🚀 WIELKA AKTUALIZACJA BlazeKillTracker v1.3

## ✅ **CO ZOSTAŁO DODANE I POPRAWIONE:**

### 🔄 **1. RZECZYWISTE AUTOMATYCZNE ZAPISYWANIE**

- **Poprzednio**: Dane zapisywane tylko przy wychodzeniu gracza z serwera
- **Teraz**: Automatyczny zapis co minutę + natychmiastowy zapis po ważnych wydarzeniach
- **Efekt**: Nigdy nie tracisz danych, nawet jeśli serwer crashuje

### 🎭 **2. SYSTEM UPRAWNIEŃ DLA LUCKPERMS**

- **Hierarchia uprawnień**: Różne poziomy dostępu dla różnych rang
- **Szczegółowe kontrolowanie**: Każda funkcja ma swoje osobne uprawnienie
- **Kompatybilność**: Pełna integracja z LuckPerms

### 🔧 **3. FUNKCJA PRZEŁADOWYWANIA PLUGINU**

- **Komenda**: `/blazekill reload`
- **Działanie**: Przeładowuje plugin bez restartu serwera
- **Bezpieczeństwo**: Zapisuje wszystkie dane przed przeładowaniem

### 📝 **4. NOWA KOMENDA UUID**

- **Komenda**: `/blazekill uuid <gracz>`
- **Działanie**: Pokazuje UUID gracza (online i offline)
- **Funkcje**: Szuka UUID w danych pluginu dla graczy offline

---

## 🏆 **HIERARCHIA UPRAWNIEŃ:**

### **👑 Administratorzy (Pełen dostęp)**

```
blazekilltracker.*
```

Daje dostęp do wszystkich funkcji pluginu

### **🔧 Moderatorzy (Zarządzanie)**

```
blazekilltracker.admin.reload      # Przeładowywanie pluginu
blazekilltracker.admin.uuid        # Sprawdzanie UUID graczy
blazekilltracker.alerts.toggle     # Włączanie/wyłączanie alertów
blazekilltracker.teleport          # Teleportacja do spawnu
blazekilltracker.tools.blockcheck  # Sprawdzanie zmian bloków
```

### **👮 Pomocnicy (Podstawowe funkcje)**

```
blazekilltracker.view.stats        # Statystyki zabójstw
blazekilltracker.view.history      # Historia spawnu graczy
blazekilltracker.view.lastspawn    # Ostatnie spawny
blazekilltracker.tools.moblog      # Łopata MobLog
```

### **👤 Zwykli gracze (Tylko podgląd)**

```
blazekilltracker.use               # Podstawowe użycie
blazekilltracker.view.stats        # Statystyki
blazekilltracker.tools.moblog      # Łopata MobLog
```

---

## 🎮 **KOMENDY I UPRAWNIENIA:**

| Komenda                   | Uprawnienie                         | Opis               |
| ------------------------- | ----------------------------------- | ------------------ |
| `/blazekill active`       | `blazekilltracker.alerts.toggle`    | Włącza alerty      |
| `/blazekill deactive`     | `blazekilltracker.alerts.toggle`    | Wyłącza alerty     |
| `/blazekill hist <gracz>` | `blazekilltracker.view.history`     | Historia spawnu    |
| `/blazekill logitem`      | `blazekilltracker.tools.moblog`     | Łopata MobLog      |
| `/blazekill lastspawn`    | `blazekilltracker.view.lastspawn`   | Ostatnie spawny    |
| `/blazekill tp <gracz>`   | `blazekilltracker.teleport`         | Teleport do spawnu |
| `/blazekill sprawdzbloki` | `blazekilltracker.tools.blockcheck` | Sprawdź bloki      |
| `/blazekill uuid <gracz>` | `blazekilltracker.admin.uuid`       | UUID gracza        |
| `/blazekill reload`       | `blazekilltracker.admin.reload`     | Przeładuj plugin   |
| `/blazekills [gracz]`     | `blazekilltracker.view.stats`       | Statystyki         |

---

## 🔧 **INSTALACJA NA LUCKPERMS:**

### **Przykładowe konfiguracje rang:**

#### **Owner/Admin:**

```
/lp group owner permission set blazekilltracker.*
```

#### **Moderator:**

```
/lp group mod permission set blazekilltracker.use true
/lp group mod permission set blazekilltracker.view.* true
/lp group mod permission set blazekilltracker.tools.* true
/lp group mod permission set blazekilltracker.alerts.toggle true
/lp group mod permission set blazekilltracker.teleport true
```

#### **Helper:**

```
/lp group helper permission set blazekilltracker.use true
/lp group helper permission set blazekilltracker.view.* true
/lp group helper permission set blazekilltracker.tools.moblog true
```

#### **Default (gracze):**

```
/lp group default permission set blazekilltracker.use true
/lp group default permission set blazekilltracker.view.stats true
/lp group default permission set blazekilltracker.tools.moblog true
```

---

## 🔄 **AUTO-SAVE SYSTEM:**

### **Jak działa:**

- **Co minutę**: Automatyczny zapis wszystkich danych
- **Natychmiast**: Po każdym ważnym wydarzeniu (spawn, alert, etc.)
- **Przy wyłączeniu**: Pełny zapis przed wyłączeniem pluginu

### **Co jest zapisywane:**

- Ustawienia alertów graczy
- Informacje o spawnie mobów
- Nametagi mobów
- Zmiany bloków
- Wszystkie inne dane pluginu

---

## 📁 **PLIK DO INSTALACJI:**

**Lokalizacja**: `target/blazekilltracker-1.0.jar`

**Instrukcje instalacji:**

1. Skopiuj plik do folderu `plugins` na serwerze
2. Zrestartuj serwer lub użyj `/blazekill reload`
3. Skonfiguruj uprawnienia w LuckPerms
4. Plugin automatycznie zacznie zapisywać dane co minutę

---

## 🎉 **KORZYŚCI Z AKTUALIZACJI:**

### ✅ **Dla Administratorów:**

- Pełna kontrola nad uprawnieniami
- Możliwość przeładowania bez restartu
- Brak utraty danych przy crash'u
- Szczegółowe informacje o graczach (UUID)

### ✅ **Dla Graczy:**

- Zawsze aktualne dane
- Brak problemów z synchronizacją
- Lepsze działanie pluginu
- Personalizowane uprawnienia

### ✅ **Dla Serwera:**

- Lepsze performance (regularne czyszczenie)
- Stabilność danych
- Łatwiejsze zarządzanie
- Kompatybilność z LuckPerms

---

## 🔍 **TESTOWANIE NOWYCH FUNKCJI:**

### **1. Testuj Auto-Save:**

```
1. Użyj spawn egg na moba
2. Sprawdź łopatą MobLog - dane powinny być zapisane
3. Nie wylogowuj się - sprawdź ponownie po minucie
4. Dane nadal powinny być dostępne
```

### **2. Testuj Uprawnienia:**

```
1. Usuń wszystkie uprawnienia gracza
2. Spróbuj użyć /blazekill - powinien pokazać błąd uprawnień
3. Dodaj blazekilltracker.use - podstawowe komendy powinny działać
4. Dodaj specyficzne uprawnienia - odpowiednie funkcje się odblokują
```

### **3. Testuj Reload:**

```
1. Użyj /blazekill reload
2. Plugin powinien zapisać dane i przeładować się
3. Wszystkie dane powinny zostać zachowane
4. Sprawdź logi konsoli dla potwierdzenia
```

---

**Plugin by jaruso99 | Wersja 1.3 | Listopad 2025**
