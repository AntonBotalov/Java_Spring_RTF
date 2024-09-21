package ru.botalov.MyFirstTestAppSpringBootLW1.func;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class updateArrayList {
    private List<String> arrayList = new ArrayList<>();

    private final Map<Integer, String> hashMap = new HashMap<>();

    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam(value = "value", defaultValue = "") String value) {
        // Добавление строки в список
        arrayList.add(value);

        // Возвращаем подтверждение
        return String.format("Значение '%s' добавлено в список. ", value);
    }

    // Метод для показа всех элементов списка
    @GetMapping("/show-array")
    public String showArrayList() {
        // Проверка, пуст ли список
        if (arrayList.isEmpty()) {
            return "Список пуст.";
        }

        // Возвращаем текущий список
        return "Текущий список: " + arrayList;
    }

    // Метод для добавления строки в HashMap
    @GetMapping("/update-map")
    public String updateHashMap(@RequestParam(value = "value", defaultValue = "") String value) {
        synchronized (hashMap) {
            // чтобы новые элементы добавлялись последовательно
            int key = hashMap.size();
            hashMap.put(key, value);
        }
        return String.format("Значение '%s' успешно добавлено в HashMap", value);
    }

    // Метод для показа всех элементов в HashMap
    @GetMapping("/show-map")
    public String showHashMap() {
        if (hashMap.isEmpty()) {
            return "HashMap пуст.";
        }
        return "Текущий HashMap: " + hashMap;
    }

    // Метод для отображения количества элементов в ArrayList и HashMap
    @GetMapping("/show-all-length")
    public String showAllLength() {
        int arrayListSize = arrayList.size();
        int hashMapSize = hashMap.size();
        return String.format("Количество элементов в ArrayList: %d, количество элементов в HashMap: %d", arrayListSize, hashMapSize);
    }
}
