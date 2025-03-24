Класс User:
Единственный метод- addUser добавляет пользователя в таблицу PostgreSQL(Далее "БД") users. В колонку goals(not null) можно вписать только "Поддержание", "Набор массы", "Похудение"
Класс Food:
Метод addFoodIntoList позволяет добавить новое блюдо в таблицу food в БД. Требует заполнения id, имени, калорий, протеинов, жиров и углеводов на 100г продукта.
Метод listOfDishes позволяет по id вытащить из БД информацию о том, какое наименование у блюда, кол-во калорий, протеинов, жиров и углеводов на 100г продукта.
Метод addMeal позволяет добавить приём пищи на указанную дату. Чтобы добавить необходимо указать idб дату, день и наименование блюда. Важно(!)- Наименование блюда может быть лишь "Паста Карбонара", "Курица терияки", "Рис отварной". На остальное стоит проверка, которая не пропустит добавление.
Метод caloriesOfTheDay позволяет взять из БД информацию на указанную дату. В неё входит: съеденные за указанную дату блюда, общее кол-во калорий, белков, жиров и углеводов.
Класс CaloriesInfo предназначен для того, чтобы высчитывать кол-во калорий для поддержания веса, набора веса и похудения по формуле Харриса-Бенедикта. Метод выдаёт кол-во калорий необходимых пользователю из колонки goals таблицы users.
