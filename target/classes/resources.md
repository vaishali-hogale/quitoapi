## Book and Publisher Table

create table if not exists publisher (
  publisherId INT PRIMARY KEY AUTO_INCREMENT,
  publisherName varchar(255)
);

create table if not exists book (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    imageUrl varchar(255),
    publisherId INT,
    FOREIGN KEY (publisherId) REFERENCES publisher(publisherId)
);

## Populating Book Data

insert into book (name, imageUrl, publisherId) values ('Harry Potter and the Sorcerer''s Stone', 'harry_potter_and_the_sorcerers_stone.jpg', 1);
insert into book (name, imageUrl, publisherId) values ('Life of Pi', 'life_of_pi.jpg', 3);
insert into book (name, imageUrl, publisherId) values ('Harry Potter and the Chamber of Secrets', 'harry_potter_and_the_chamber.jpg', 1);
insert into book (name, imageUrl, publisherId) values ('East of Eden', 'east_of_eden.jpg', 6);
insert into book (name, imageUrl, publisherId) values ('The 3 Mistakes of My Life', 'the_3_mistakes_of_my_life.jpg', 2);
insert into book (name, imageUrl, publisherId) values ('One Night at the Call Center', 'one_night_at_the_call_center.jpg', 2);
insert into book (name, imageUrl, publisherId) values ('Half Girlfriend', 'half_girlfriend.jpg', 2);
insert into book (name, imageUrl, publisherId) values ('The Secret', 'the_secret.jpg', 4);
insert into book (name, imageUrl, publisherId) values ('The Rise of Theodore Roosevelt', 'the_rise_of_theodore_roosevelt.jpg', 5);