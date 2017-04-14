DROP TABLE IF EXISTS shops CASCADE;
DROP TABLE IF EXISTS producers CASCADE ;
DROP TABLE IF EXISTS products CASCADE ;
DROP TABLE IF EXISTS shops_products CASCADE ;

CREATE TABLE shops
(
  shop_id      INTEGER PRIMARY KEY,
  shop_name    VARCHAR(255) NOT NULL,
  shop_city    VARCHAR(255) NOT NULL,
  shop_street  VARCHAR(255) NOT NULL
);

CREATE TABLE producers
(
  producer_id     INTEGER PRIMARY KEY,
  producer_name   VARCHAR(255) NOT NULL
);

CREATE TABLE products
(
  product_id      INTEGER PRIMARY KEY,
  product_name    VARCHAR(255) NOT NULL,
  producer_id     INTEGER NOT NULL,
  FOREIGN KEY (producer_id) REFERENCES producers (producer_id) ON UPDATE CASCADE
);

CREATE TABLE shops_products
(
  shop_id         INTEGER,
  product_id      INTEGER,
  PRIMARY KEY (shop_id, product_id),
  FOREIGN KEY (shop_id) REFERENCES shops (shop_id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (product_id) REFERENCES products (product_id) ON UPDATE CASCADE
);
