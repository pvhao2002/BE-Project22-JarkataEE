CREATE DATABASE project22;
USE project22;

CREATE TABLE `role`
(
   `id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255)
);
INSERT INTO `role`(`name`) VALUES ('ADMIN');
INSERT INTO `role`(`name`) VALUES ('CLIENT');

CREATE TABLE `users`
(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`username` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	`fullname` VARCHAR(255) NOT NULL,
	`role_id` INT,
	`is_deleted` TINYINT DEFAULT 0,
	CONSTRAINT fk_user_role FOREIGN KEY(`role_id`) REFERENCES `role`(`id`)
);

CREATE TABLE `product`
(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`product_name` VARCHAR(255) NOT NULL,
	`description` VARCHAR(255) NOT NULL,
	`category` VARCHAR(255) DEFAULT 'N/A',
	`author` VARCHAR(255) DEFAULT 'UNKNOW',
	`price`		FLOAT DEFAULT 0,
	`image` VARCHAR(255),
	`is_deleted` TINYINT DEFAULT 0
);

CREATE TABLE	`order`
(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`user_id` INT,
	`address`	VARCHAR(255),
	`phone`		VARCHAR(255),
	`status`	ENUM('Pending', 'Success', 'Failed') NOT NULL,
	`note`      VARCHAR(555),
	`total`		FLOAT,
	CONSTRAINT fk_order_user FOREIGN KEY(`user_id`) REFERENCES `users`(`id`)
);

CREATE TABLE `order_item`
(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`order_id` INT,
	`product_id` INT,
	`quantity`	INT,
	`total_price` FLOAT,
	CONSTRAINT fk_orderitem_order FOREIGN KEY(`order_id`) REFERENCES `order`(`id`),
	CONSTRAINT fk_orderitem_product FOREIGN KEY(`product_id`) REFERENCES `product`(`id`)
);



INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (4, 'Điều kỳ diệu của việc đọc', 'Cuốn sách này sẽ giúp bạn hiểu được tầm quan trọng của việc đọc, và cách đọc hiệu quả.', 'Sách giáo dục', 'Rita Mae Brown', 150000, 'https://photo-cms-tinnhanhchungkhoan.epicdn.me/w1200/Uploaded/2023/cqjwqcqdh/2016_11_01/docsachtruockhingu2doanhnhansaigon_UXXB.jpg', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (5, 'Cuốn theo chiều gió', 'Cuốn tiểu thuyết kinh điển của Mỹ kể về câu chuyện tình yêu giữa Scarlett OHara và Rhett Butler trong bối cảnh Nội chiến Hoa Kỳ.', 'Tiểu thuyết', 'Margaret Mitchell', 150000, 'https://salt.tikicdn.com/cache/w1200/ts/product/a6/1e/c9/bb2b006d46f1c6c54f9c66eac99ca7ae.jpg', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (6, 'Harry Potter và hòn đá phù thủy', 'Cuốn sách đầu tiên trong bộ truyện Harry Potter, kể về câu chuyện của cậu bé phù thủy Harry Potter khi cậu được nhận vào trường Hogwarts.', 'Tiểu thuyết thiếu nhi', 'J.K. Rowling', 150000, 'https://vietcanbooks.com/cdn/shop/files/harry-potter-sorcerers-stone-tap-1-vietcanbooks.webp?v=1685489430', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (7, 'Tuổi trẻ đáng giá bao nhiêu', 'Cuốn sách truyền cảm hứng kể về hành trình trưởng thành của tác giả, từ một cậu bé nghèo khó ở quê lên thành phố học tập và lập nghiệp.', 'Sách non-fiction', 'Vũ Cát Tường', 150000, 'https://salt.tikicdn.com/cache/w1200/media/catalog/product/t/u/tuoi-tre-dang-gia-bao-nhieu-u547-d20161012-t113832-888179.u3059.d20170616.t095744.390222.jpg', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (8, 'Đắc nhân tâm', 'Cuốn sách kinh điển về nghệ thuật giao tiếp và ứng xử, giúp bạn thành công trong cuộc sống.', 'Sách self-help', 'Dale Carnegie', 150000, 'https://salt.tikicdn.com/cache/w1200/ts/product/df/7d/da/d340edda2b0eacb7ddc47537cddb5e08.jpg', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (9, 'Chiến tranh và hòa bình', 'Tiểu thuyết kinh điển của Nga kể về câu chuyện của những người dân Nga trong bối cảnh Chiến tranh Napoleon.', 'Tiểu thuyết', 'Lev Tolstoy', 150000, 'https://static.8cache.com/cover/o/eJzLyTDW13UxrowPDbLIiArz1Q-ryk3yTY4w0w3z1HeEAv_MQP3w0Dx3E5PkcgtTT8eUUMfMiKKo7CiDMAsDf4Oy4ND08HJn53z9YgMA3bAYqw==/chien-tranh-va-hoa-binh.jpg', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (10, 'Điều kỳ diệu của việc sống', 'Cuốn sách truyền cảm hứng kể về hành trình vượt qua nghịch cảnh của tác giả, từ một người bị liệt tứ chi đến trở thành một vận động viên và diễn giả nổi tiếng.', 'Sách non-fiction', 'Christopher Reeve', 150000, 'https://www.dtv-ebook.com/images/Cover/34297216835_d0f8d26894_z.jpg', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (11, 'Người giàu có nhất thành Babylon', 'Cuốn sách kinh điển về tài chính cá nhân, dạy bạn cách làm giàu và quản lý tài chính hiệu quả.', 'Sách self-help', 'George S. Clason', 150000, 'https://upload.wikimedia.org/wikipedia/vi/0/04/Ng%C6%B0%E1%BB%9Di_gi%C3%A0u_c%C3%B3_nh%E1%BA%A5t_th%C3%A0nh_Babylon.jpg', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (12, 'Thiết lập mục tiêu', 'Cuốn sách hướng dẫn bạn cách thiết lập và đạt được mục tiêu của mình.', 'Sách self-help', 'Brian Tracy', 150000, 'https://cdn0.fahasa.com/media/flashmagazine/images/page_images/thuat_thiet_lap_va_hoan_thanh_muc_tieu/2022_06_17_16_09_28_1-390x510.jpg', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (13, '7 thói quen của người thành đạt', 'Cuốn sách kinh điển về phát triển bản thân, dạy bạn cách xây dựng những thói quen tốt để thành công trong cuộc sống.', 'Sách self-help', 'Stephen R. Covey', 150000, 'https://salt.tikicdn.com/cache/w1200/ts/product/80/33/dc/7a68a9653451c10accb96d09abf5dbe2.png', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (14, 'Nhà giả kim', 'Cuốn tiểu thuyết phiêu lưu kể về hành trình của Santiago, một cậu bé chăn cừu người Tây Ban Nha, trên con đường tìm kiếm kho báu ẩn giấu trong kim tự tháp Ai Cập.', 'Tiểu thuyết', 'Paulo Coelho', 150000, 'https://upload.wikimedia.org/wikipedia/vi/thumb/9/9c/Nh%C3%A0_gi%E1%BA%A3_kim_%28s%C3%A1ch%29.jpg/150px-Nh%C3%A0_gi%E1%BA%A3_kim_%28s%C3%A1ch%29.jpg', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (15, 'Tiểu thuyết lãng mạn', 'Cuốn sách kể về câu chuyện tình yêu giữa hai nhân vật chính, từ lúc gặp gỡ, yêu nhau đến khi vượt qua mọi thử thách để đến được với nhau.', 'Tiểu thuyết', 'Vũ Ngọc Trân', 150000, 'https://anh.eva.vn/upload/3-2017/images/2017-07-08/1499529663-tieu-thuyet-lang-man-ava.jpg', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (16, 'Thiên đường và địa ngục', 'Cuốn sách tôn giáo kể về cuộc hành trình của Dante Alighieri, một nhà thơ người Ý, xuống địa ngục và lên thiên đường.', 'Tiểu thuyết', 'Dante Alighieri', 150000, 'https://nhanhoc.edu.vn/wp-content/uploads/2021/05/187073398_5780457562025989_6562474723532751207_n.jpg', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (17, 'Hạt giống tâm hồn', 'Cuốn sách gồm những câu chuyện ngắn, ý nghĩa về cuộc sống, tình yêu, gia đình, và những bài học cuộc sống.', 'Sách self-help', 'Vũ Cát Tường', 150000, 'https://thuvienhoasen.org/images/file/SFO5nWNm2ggBAmBH/hat-giong-tam-hon.png', 0);
INSERT INTO project22.product (id, product_name, description, category, author, price, image, is_deleted) VALUES (18, 'Nghệ thuật sống', 'Cuốn sách bàn về những vấn đề triết học, nhân sinh, và nghệ thuật sống một cách sâu sắc và thấm thía.', 'Sách self-help', 'Epictetus', 150000, 'https://product.hstatic.net/1000075554/product/nghe-thuat-song-yw3na_d21dd2684ad5423cab2d49a6ee30d7fb_e4c9095660c2441ea83bf905cd1d1ec3.jpg', 0);
