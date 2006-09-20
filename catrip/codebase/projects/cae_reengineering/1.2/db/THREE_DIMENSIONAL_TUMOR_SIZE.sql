
CREATE TABLE THREE_DIMENSIONAL_TUMOR_SIZE
(
  ID                      NUMBER(10,2)          NOT NULL,
  GREATEST_DIMENSION      INTEGER,
  ADDITIONAL_DIMENSION_Y  INTEGER,
  ADDITIONAL_DIMENSION_Z  INTEGER,
  MVR                     VARCHAR2(255 BYTE)
)
LOGGING 
NOCACHE
NOPARALLEL;


ALTER TABLE THREE_DIMENSIONAL_TUMOR_SIZE ADD (
  PRIMARY KEY (ID));


ALTER TABLE THREE_DIMENSIONAL_TUMOR_SIZE ADD (
  CONSTRAINT FK_THREE_DIMENSIONAL_SIZES FOREIGN KEY (ID) 
    REFERENCES ANNOTATION_SET (ID));



