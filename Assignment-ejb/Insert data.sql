INSERT INTO FIT5042.MUSEUM (MUSEUM_ID, COLLECTION_DESCRIPTION, LEARN_MORE_BODY, LEARN_MORE_HEADING, LEARN_MORE_LEADING, LEARN_MORE_TITLE, MUSEUM_CHIEF_CURATOR, MUSEUM_CONTACT_NUMBER, MUSEUM_IMAGE_PATH, MUSEUM_NAME, POSTCODE, "STATE", STREET_ADDRESS, STREET_NUMBER, SUBURB) 
	VALUES (1, 'Hand-designed Artifacts by Australian Aboriginals Exhibited in the Museums  ', 'Aboriginal Australians are the various indigenous peoples of the Australian mainland, Tasmania, and often the Tiwi Islands. This group contains many distinct peoples that have developed across Australia for over 50,000 years. These peoples have a broadly shared, though complex, genetic history, but it is only in the last two hundred years that they have been defined and started to self identify as a single group. The definition of the term "Aboriginal" has changed over time and place, with the importance of family lineage, self identification and community acceptance all being of varying importance.', 'About the people', 'A number of practices and ceremonies, based on a belief in the Dreamtime', 'Learn about Aboriginal Culture', 'Dr. Koby McMahon', '0249561357', 'img/ngv.jpg', 'Museum of Aboriginal Artifacts', '3161', 'VIC', 'Glen Eira Road', '373', 'Caulfield North');
INSERT INTO FIT5042.MUSEUM (MUSEUM_ID, COLLECTION_DESCRIPTION, LEARN_MORE_BODY, LEARN_MORE_HEADING, LEARN_MORE_LEADING, LEARN_MORE_TITLE, MUSEUM_CHIEF_CURATOR, MUSEUM_CONTACT_NUMBER, MUSEUM_IMAGE_PATH, MUSEUM_NAME, POSTCODE, "STATE", STREET_ADDRESS, STREET_NUMBER, SUBURB) 
	VALUES (2, NULL, NULL, NULL, NULL, NULL, 'Dr. Hunter Brunton', '0240493021', 'img/am.jpg', 'Australian Museum', '2830', 'NSW', 'Ulomogo Street', '88', 'Rocky Road');
INSERT INTO FIT5042.MUSEUM (MUSEUM_ID, COLLECTION_DESCRIPTION, LEARN_MORE_BODY, LEARN_MORE_HEADING, LEARN_MORE_LEADING, LEARN_MORE_TITLE, MUSEUM_CHIEF_CURATOR, MUSEUM_CONTACT_NUMBER, MUSEUM_IMAGE_PATH, MUSEUM_NAME, POSTCODE, "STATE", STREET_ADDRESS, STREET_NUMBER, SUBURB) 
	VALUES (3, NULL, NULL, NULL, NULL, NULL, 'Dr. Eden Scarfe', '0298827128', 'img/moc.jpg', 'Museum of Culture', '2101', 'NSW', 'Queen Street', '92', 'North Narrabeen');

INSERT INTO FIT5042.COLLECTION (COLLECTION_ID, COLLECTION_CATEGORY, COLLECTION_CURATOR, COLLECTION_DESCRIPTION, COLLECTION_IMAGE_PATH, COLLECTION_NAME, MUSEUM_ID) 
	VALUES (1, 'Tools', 'Dr. Isabelle Cunningham', 'The aboriginals use a variety of tools, find out more about them here!', 'img/collections/1/tools.jpg', 'Aboriginal Tools', 1);
INSERT INTO FIT5042.COLLECTION (COLLECTION_ID, COLLECTION_CATEGORY, COLLECTION_CURATOR, COLLECTION_DESCRIPTION, COLLECTION_IMAGE_PATH, COLLECTION_NAME, MUSEUM_ID) 
	VALUES (2, 'Weapons', 'Dr. Piper Dangar', 'Ever wondered how people used to hunt before guns were invented? You''ve come to the right place!', 'img/collections/1/weapons.jpg', 'Hunting Weapons', 1);
INSERT INTO FIT5042.COLLECTION (COLLECTION_ID, COLLECTION_CATEGORY, COLLECTION_CURATOR, COLLECTION_DESCRIPTION, COLLECTION_IMAGE_PATH, COLLECTION_NAME, MUSEUM_ID) 
	VALUES (3, 'Craft', 'Dr. Alyssa Coaldrake', 'See the various amazing weaving and painting techniques used by the aboriginal people', 'img/collections/1/craft.jpg', 'Arts & Craft', 1);
INSERT INTO FIT5042.COLLECTION (COLLECTION_ID, COLLECTION_CATEGORY, COLLECTION_CURATOR, COLLECTION_DESCRIPTION, COLLECTION_IMAGE_PATH, COLLECTION_NAME, MUSEUM_ID) 
	VALUES (4, 'Arts', 'Dr. Brayden Kirkwood', 'A picture says a 1000 words!', 'img/collections/2/paint.jpg', 'Paint on Canvas', 2);
INSERT INTO FIT5042.COLLECTION (COLLECTION_ID, COLLECTION_CATEGORY, COLLECTION_CURATOR, COLLECTION_DESCRIPTION, COLLECTION_IMAGE_PATH, COLLECTION_NAME, MUSEUM_ID) 
	VALUES (5, 'Sculptures', 'Dr. Zoe Schlunke', 'See the detail in painstakingly handcrafted sculptures by famous artists like Michaelangelo', 'img/collections/2/sculptures.jpg', 'Marble Sculptures', 2);
INSERT INTO FIT5042.COLLECTION (COLLECTION_ID, COLLECTION_CATEGORY, COLLECTION_CURATOR, COLLECTION_DESCRIPTION, COLLECTION_IMAGE_PATH, COLLECTION_NAME, MUSEUM_ID) 
	VALUES (6, 'History', 'Dr. Madeline Goffage', 'Revisit the time that was, starting all the way back to 700 B.C.!', 'img/collections/2/antiquities.jpg', 'Antiquities', 2);
INSERT INTO FIT5042.COLLECTION (COLLECTION_ID, COLLECTION_CATEGORY, COLLECTION_CURATOR, COLLECTION_DESCRIPTION, COLLECTION_IMAGE_PATH, COLLECTION_NAME, MUSEUM_ID) 
	VALUES (7, 'History', 'Dr. Cameron Gladys', 'The Chinese emperor''s 10,000 terracotta warriors, in all their glory', 'img/collections/3/terracotta.jpg', 'Sun Tzu''s Terracotta Army', 3);
INSERT INTO FIT5042.COLLECTION (COLLECTION_ID, COLLECTION_CATEGORY, COLLECTION_CURATOR, COLLECTION_DESCRIPTION, COLLECTION_IMAGE_PATH, COLLECTION_NAME, MUSEUM_ID) 
	VALUES (8, 'Clothes', 'Dr. Lauren Howden', 'How clothes were made and the fashion of the time revisited', 'img/collections/3/clothes.jpg', 'Dressing up', 3);
INSERT INTO FIT5042.COLLECTION (COLLECTION_ID, COLLECTION_CATEGORY, COLLECTION_CURATOR, COLLECTION_DESCRIPTION, COLLECTION_IMAGE_PATH, COLLECTION_NAME, MUSEUM_ID) 
	VALUES (9, 'Texts', 'Dr. Jacob Bentham', 'The ancient, sacred texts of the people. Very few copies survive today!', 'img/collections/3/writings.jpg', 'Writings on parchment', 3);

INSERT INTO FIT5042.EXHIBITION (EXHIBITION_ID, EXHIBITION_DESCRIPTION, EXHIBITION_END_DATE, EXHIBITION_NAME, EXHIBITION_START_DATE, IMAGE_PATH, TICKET_DESCRIPTION, TICKET_NAME, TICKET_PRICE, COLLECTION_ID, MUSEUM_ID) 
	VALUES (1, 'See how the aboriginals practiced the art of war!', '2019-12-31', 'Fighting', '2019-09-22', 'img/exhibitions/1/shield.jpg', 'Entry to the fighting section. Includes a tour of the museum.', 'Standard', 15.0, 1, 1);
INSERT INTO FIT5042.EXHIBITION (EXHIBITION_ID, EXHIBITION_DESCRIPTION, EXHIBITION_END_DATE, EXHIBITION_NAME, EXHIBITION_START_DATE, IMAGE_PATH, TICKET_DESCRIPTION, TICKET_NAME, TICKET_PRICE, COLLECTION_ID, MUSEUM_ID) 
	VALUES (2, 'Before there were butchers, if you wanted meat, you had to hunt it!', '2019-12-31', 'Hunting', '2019-09-22', 'img/exhibitions/1/boomerang.jpg', NULL, 'Standard', 15.0, 1, 1);
INSERT INTO FIT5042.EXHIBITION (EXHIBITION_ID, EXHIBITION_DESCRIPTION, EXHIBITION_END_DATE, EXHIBITION_NAME, EXHIBITION_START_DATE, IMAGE_PATH, TICKET_DESCRIPTION, TICKET_NAME, TICKET_PRICE, COLLECTION_ID, MUSEUM_ID) 
	VALUES (3, 'Amazing art from the natives', '2019-12-31', 'Painting', '2019-09-22', 'img/exhibitions/1/snakes.jpg', NULL, 'Standard', 15.0, 2, 1);
INSERT INTO FIT5042.EXHIBITION (EXHIBITION_ID, EXHIBITION_DESCRIPTION, EXHIBITION_END_DATE, EXHIBITION_NAME, EXHIBITION_START_DATE, IMAGE_PATH, TICKET_DESCRIPTION, TICKET_NAME, TICKET_PRICE, COLLECTION_ID, MUSEUM_ID) 
	VALUES (4, 'Growing food crops and rearing animals', '2019-12-31', 'Farming', '2019-09-22', 'img/exhibitions/1/bullroarer.jpg', NULL, 'Standard', 15.0, 2, 1);
INSERT INTO FIT5042.EXHIBITION (EXHIBITION_ID, EXHIBITION_DESCRIPTION, EXHIBITION_END_DATE, EXHIBITION_NAME, EXHIBITION_START_DATE, IMAGE_PATH, TICKET_DESCRIPTION, TICKET_NAME, TICKET_PRICE, COLLECTION_ID, MUSEUM_ID) 
	VALUES (5, 'Collecting leaves and crop', '2019-12-31', 'Gathering', '2019-09-22', 'img/exhibitions/1/basket.jpg', NULL, 'Standard', 15.0, 3, 1);
