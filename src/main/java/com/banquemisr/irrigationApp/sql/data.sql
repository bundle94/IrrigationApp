-- Create table statement for PLOT table
CREATE TABLE IF NOT EXISTS PLOT
(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
size VARCHAR(25),
surveyed BIT,
geo_cordinates VARCHAR(25),
last_date_irrigated DATE,
crop VARCHAR(25)
);

--Create table statement for PLOT table
CREATE TABLE IF NOT EXISTS TIME_SLOT (
  id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
  plot_id int NOT NULL,
  time TIME,
  amount_of_water_needed DOUBLE,
  status VARCHAR(25),
  CONSTRAINT Plot_Timeslot_FK FOREIGN KEY (plot_id)
    REFERENCES Plot (id)
);