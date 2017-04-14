DELETE FROM shops;
DELETE FROM products;
DELETE FROM producers;
DELETE FROM shops_products;

INSERT INTO shops (shop_id, shop_name, shop_city, shop_street) VALUES
  (1, 'Shop For Exampleывыв', 'St. Petersburg', 'Street'),
  (2, 'Shop For Example2', 'St. Petersburg2', 'Street 2'),
  (3, 'Shop For Example3', 'St. Petersburg3', 'Street 3'),
  (4, 'Shop For Example4', 'St. Petersburg4', 'Street 4'),
  (5, 'Shop For Example5', 'St. Petersburg5', 'Street 5'),
  (6, 'Shop For Example6', 'St. Petersburg6', 'Street 6'),
  (7, 'Shop For Example7', 'St. Petersburg7', 'Street 7'),
  (8, 'Shop For Example8', 'St. Petersburg8', 'Street 8'),
  (9, 'Shop For Example9', 'St. Petersburg9', 'Street 9'),
  (10, 'Shop For Example10', 'St. Petersburg10', 'Street 10'),
  (11, 'Shop For Example11', 'St. Petersburg11', 'Street 11'),
  (12, 'Shop For Example12', 'St. Petersburg12', 'Street 12'),
  (13, 'Shop For Example13', 'St. Petersburg13', 'Street 13'),
  (14, 'Shop For Example14', 'St. Petersburg14', 'Street 14'),
  (15, 'Shop For Example15', 'St. Petersburg15', 'Street 15');

INSERT INTO producers (producer_id, producer_name) VALUES
  (201, 'producer 1'),
  (202, 'producer 2'),
  (203, 'producer 3'),
  (204, 'producer 4');

INSERT INTO products (product_id, product_name, producer_id) VALUES
  (101, 'product 1', 201),
  (102, 'product 2', 201),
  (103, 'product 3', 201),

  (104, 'product 4', 202),
  (105, 'product 5', 202),

  (106, 'product 6', 203),

  (107, 'product 7', 204);

INSERT INTO shops_products(shop_id, product_id) VALUES
  (1, 101),
  (1, 103),
  (1, 104),
  (1, 106),
  (1, 107),

  (2, 102),
  (2, 105),
  (2, 106),

  (3, 103);