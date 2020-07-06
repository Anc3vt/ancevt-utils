CREATE TABLE IF NOT EXISTS companies (
	id INTEGER PRIMARY KEY NOT NULL,
	name VARCHAR(255) NOT NULL,
	info TEXT,
	info_html TEXT,
	area VARCHAR(32),
	page_url VARCHAR(255) NOT NULL,
	web_site VARCHAR(255),
	trusted INTEGER(1) NOT NULL,
	image_data BLOB,
	image_alt VARCHAR(32),
	image_source VARCHAR(255),
	image_mime_type VARCHAR(64)
);