
![Screenshot](https://github.com/user-attachments/assets/324e8c59-1330-4c7b-be8a-a3f86ed180c5)

# Описание программы «Симуляция».
Программа выполняет симуляцию жизни существ и элементов окружающей среды.

## В меню настроек можно менять:
- **размер карты**
- **цвет поля**
- **возможность выбора существ**
- **скорость рендеринга симуляции**

Настройки сохраняются и доступны при следующем запуске программы.

## Жизненный цикл существ:
Существа на карте разбиты на две группы - Хищники и Травоядные. Каждая группа
делает поочерёдно ход. Хищники могут атаковать Травоядных и увеличивать здоровье, но при выполнении хода
они теряют здоровье. Травоядные же могут атаковать Растения, тем самым пополняя здоровье, но теряют его
при атаке со стороны Хищников. Программа на каждой итерации симуляции рассчитывает кратчайший путь между
сущностями - между Хищником и Травоядным, либо между Травоядным и Растением. В случае приближения Хищника
Травоядные ищут способ максимально увеличить дистанцию, сделав шаг.
Симуляция завершается когда погибает вся популяция той или иной группы существ.

## Особенности:
Для поиска кратчайшего пути используется алгоритм поиска A*.

Для управления симуляцией открывается дополнительное небольшое окно, куда нужно ввести цифры для изменения
состояния симуляции.

Программа имеет возможность сохранения настроек, а также может выполнить восстановление файла настроек,
в случае если внесены некорректные данные, либо файл повреждён.
