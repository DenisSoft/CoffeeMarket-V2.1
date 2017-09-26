drop database coffee_market_db;
create database coffee_market_db;
use coffee_market_db;

create table CoffeeType (
  id bigint auto_increment, -- pk
  name_ru varchar(200) not null, -- название
  description_ru varchar(1000) not null, -- описание
  name_en varchar(200) not null, -- название
  description_en varchar(1000) not null, -- описание
  image varchar(50), -- фото
  price double not null, -- цена
  disabled char(1), -- если disabled = 'Y', то не показывать данный сорт в списке доступных сортов
  primary key (id)
);

create index CT_I on CoffeeType (
  id asc
);

--
-- Заказ
--
create table CoffeeOrder (
  id bigint auto_increment, -- pk
  order_date datetime not null, -- время заказа
  name varchar(100), -- имя заказчика
  email varchar(100), -- email заказчика
  phone varchar(100), -- телефон заказчика
  delivery_address varchar(200) not null, -- куда доставлять
  cost double, -- сколко стоит (алгоритм подсчёта может поменяться, поэтому надо хранить стоимость)
  primary key (id)
);

create index CO_I1 on CoffeeOrder (
  id asc
);

--
-- Одна позиция заказа
--
create table CoffeeOrderItem (
  id bigint auto_increment, -- pk
  type_id bigint not null, -- сорт кофе
  order_id bigint not null, -- к какому заказу принадлежит
  quantity int, -- сколько чашек
  primary key (id)
);

create index COI_I on CoffeeOrderItem (
  order_id asc
);

create index COI_3 on CoffeeOrderItem (
  type_id asc
);

alter table CoffeeOrderItem
  add constraint COI_CO foreign key (order_id)
references CoffeeOrder (id);


alter table CoffeeOrderItem
  add constraint COI_CT foreign key (type_id)
references CoffeeType (id);

--
-- Конфигурация
--
create table Configuration (
  id char (1) not null, -- pk, название свойства
  value int, -- значение
  primary key (id)
);

create table blockquote (
  id bigint auto_increment,
  text_ru varchar(1000), -- значение
  author_ru varchar(100), -- автор
  text_en varchar(1000), -- значение
  author_en varchar(100), -- автор
  primary key (id)
);

insert into blockquote (text_ru, author_ru, text_en, author_en) values ('Единственное, что важно,
            когда наливают кофе из серебрянного носика, — то, как струя начинает светиться, то, 
            как густой коричневый цвет становится янтарным и золотисто-желтым, а струя, извивающаяся 
            как танцор, пока наполняется чашка, вдруг обрывается, словно призрак спрятался обратно 
            в кофейник.', 'Энн Райс, "Скрипка"', 'The only thing that is important, when poured 
            coffee from a silver spout - then, as the stream begins to glow, then, as a thick brown 
            color becomes amber and golden yellow, and a jet wriggling as a dancer, while the cup 
            is filling, suddenly breaks, like a ghost hiding back in the coffee pot.', 'Anne Rice, 
            "The Violin"');
insert into blockquote (text_ru, author_ru, text_en, author_en) values ('Поднять настроение могут 
            хорошие друзья, хороший кофе и… пятница :-)', 'Башорг', 'To cheer up can
             good friends, good coffee and ... friday :-)', 'Bashorh');
insert into blockquote (text_ru, author_ru, text_en, author_en) values ('Изысканные ароматы кофе 
            витают над страницами этого романа. Просто представьте, о чем она столько мыслей сразу! 
            Как будто кофе — это что-то вроде приманивающего зелья, не знаю, почему, но у меня в 
            голове это!', 'Энтони Капелла, "Вкус кофе"', 'Exquisite coffee aromas Hover over the 
            pages of this novel. Just imagine what she thinks about all at once! As if coffee is 
            something like a luring potion, I do not know why, but I have head it!', 'Anthony 
            Capella, "The Taste of Coffee"');
insert into blockquote (text_ru, author_ru, text_en, author_en) values ('Пить кофе ночью — это как 
            первый снег или как раннее утро после бури: всегда кажется, что что-то похожее уже было 
            в твой жизни...', 'Банана Ёсимото, "Амрита"', 'Drinking coffee at night is like The 
            first snow or like an early morning after a storm: it always seems that something 
            similar has already happened in your life ... ', 'Banana Yoshimoto, "Amrita"');

insert into coffeeorder (order_date, name, email, phone, delivery_address, cost) values 
            ('2017-02-10', 'Иван', 'ivan@dk.ru', '+375(29)6849032', 'г.Минск, ул.Голодеда 75-7', 17);
insert into coffeeorder (order_date, name, email, phone, delivery_address, cost) values 
            ('2016-03-16', 'Антон', 'anton@dk.ru', '+375(29)9759032', 'г.Минск, пр.Рокоссовского 4-4-435', 13);
            
insert into coffeetype (name_ru, description_ru, name_en, description_en, price, image, disabled) 
            values ('Американо', 'Напиток придумали в Италии ещё во время Второй мировой войны для американцев 
            как аналог американского популярного фильтрового напитка «регуляр». Общим у этих двух напитков 
            были лишь большой объём.', 'Americano', 'Americano is a style of coffee prepared by brewing 
            espresso with added hot water, giving it a similar strength to, but different flavor from drip 
            coffee. The strength of an Americano varies with the number of shots of espresso and the amount 
            of water added.', 1, 'Americano.jpg', 'N');
insert into coffeetype (name_ru, description_ru, name_en, description_en, price, image, disabled) 
            values ('Латте макиато', 'Ла́тте макиа́то — горячий кофейный напиток, приготавливаемый путём вливания 
            в молоко кофе-эспрессо в пропорции 3:1. Итальянское macchia обозначает маленькое пятнышко кофе, 
            остающееся на поверхности молочной пены.', 'Latte macchiato', 'Latte macchiato is a coffee beverage; 
            the name literally means stained milk. This refers to the method of preparation, wherein the milk 
            is "stained" by the addition of espresso.', 3, 'Latte Macchiato.jpg', 'N'); 
insert into coffeetype (name_ru, description_ru, name_en, description_en, price, image, disabled) 
            values ('Эспрессо', 'Эспрессо — метод приготовления кофе путём прохождения горячей воды (около 90 °C) под 
            давлением 8-10 бар через фильтр с молотым кофе.Эспрессо пользуется большой популярностью во всём 
            мире и, прежде всего, на юге Европы — в Италии, Испании и Португалии.', 'Espresso', 'Espresso 
            is coffee brewed by forcing a small amount of nearly boiling water under pressure through finely 
            ground coffee beans. Espresso is generally thicker than coffee brewed by other methods, has a 
            higher concentration of suspended and dissolved solids, and has crema on top (a foam with a 
            creamy consistency).', 4, 'Espresso.jpg', 'N'); 
insert into coffeetype (name_ru, description_ru, name_en, description_en, price, image, disabled) 
            values ('Капучино', 'Происхождение названия напитка связано с тем, что в Европе XVII века название 
            ордена капуцинов служило, в частности, и для обозначения характерного цвета (красно-коричневого), 
            который имели рясы монахов этого ордена; в XVIII веке так же стали называть кофейный напиток из 
            яичных желтков и сливок, который стали готовить в Австрии (нем. Kapuziner). Итальянская форма 
            названия напитка (cappuccino) фиксируется лишь с XX века.', 'Cappuccino', 'The Viennese bestowed 
            the name "Kapuziner" possibly in the 18th century on a version that included whipped cream and 
            spices of unknown origin. The Italian cappuccino was unknown outside Italy until the 1930s, and 
            seems to be born out of Viennese-style cafés in Trieste and other cities in the former Austria in 
            the first decades of the 20th century.', 2, 'Cappuccino.jpg', 'N'); 
insert into coffeetype (name_ru, description_ru, name_en, description_en, price, disabled) 
            values ('Маккочино', 'Моккачино — кофейный напиток, созданный в Америке и являющийся разновидностью 
            латте с добавлением шоколада. Название «моккачино» используется в Европе. В Северной Америке этот 
            напиток известен как «мокко».', 'Mocaccino', 'A caffè mocha also called mocaccino is a 
            chocolate-flavored variant of a caffè latte. Other commonly used spellings are mochaccino and 
            also mochachino.', 2, 'Y'); 
            
            
insert into coffeeorderitem (type_id, order_id, quantity) values (1, 1, 1);
insert into coffeeorderitem (type_id, order_id, quantity) values (2, 1, 2);
insert into coffeeorderitem (type_id, order_id, quantity) values (4, 1, 5);
insert into coffeeorderitem (type_id, order_id, quantity) values (1, 2, 3);
insert into coffeeorderitem (type_id, order_id, quantity) values (3, 2, 1);
insert into coffeeorderitem (type_id, order_id, quantity) values (4, 2, 3);

insert into configuration (id, value) values ('m', 2);
insert into configuration (id, value) values ('n', 5);
insert into configuration (id, value) values ('x', 10);
