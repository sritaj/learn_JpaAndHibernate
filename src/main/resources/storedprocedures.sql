select * from actionfigures;

DELIMITER //

CREATE PROCEDURE GetAllActionFigures()
BEGIN
	SELECT *  FROM actionfigures;
END //


DELIMITER //

CREATE PROCEDURE GetAllActionFiguresByPrice(IN price_in decimal)
BEGIN
	SELECT *  FROM actionfigures where price>price_in;
END //

DELIMITER //

CREATE PROCEDURE GetAllActionFiguresByPrice(IN price_in decimal)
BEGIN
	SELECT count(*)  FROM actionfigures where price>price_in;
END //

drop procedure GetAllActionFigures;
drop procedure GetAllActionFiguresByPrice;
drop procedure GetAllActionFiguresByPrice;