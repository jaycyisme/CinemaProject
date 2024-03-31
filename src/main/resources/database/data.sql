INSERT INTO Role (Code, RoleName)
VALUES ('Admin', 'ROLE_ADMIN');
INSERT INTO Role (Code, RoleName)
VALUES ('Mod', 'ROLE_MODERATOR');
INSERT INTO Role (Code, RoleName)
VALUES ('User', 'ROLE_USER');

INSERT INTO UserStatus (Code, Name)
VALUES ('False', 'InActive');
INSERT INTO UserStatus (Code, Name)
VALUES ('True', 'Active');

INSERT INTO BillStatus (Name)
VALUES ('InActive');
INSERT INTO BillStatus (Name)
VALUES ('Active');

INSERT INTO SeatType (NameType)
VALUES ('Standard');
INSERT INTO SeatType (NameType)
VALUES ('VIP');

INSERT INTO SeatStatus (Code, NameStatus)
VALUES ('False', 'Occupied');
INSERT INTO SeatStatus (Code, NameStatus)
VALUES ('True', 'Available');

INSERT INTO Rate (Description, Code)
VALUES ('1 star', 'Bad');
INSERT INTO Rate (Description, Code)
VALUES ('2 star', 'Good');
INSERT INTO Rate (Description, Code)
VALUES ('3 star', 'Great');


INSERT INTO RankCustomer (Point, Description, Name, IsActive)
VALUES (0, 'None Rank', 'None', true);
INSERT INTO RankCustomer (Point, Description, Name, IsActive)
VALUES (20, 'Bronze Rank', 'Bronze', true);
INSERT INTO RankCustomer (Point, Description, Name, IsActive)
VALUES (40, 'Silver Rank', 'Silver', true);
INSERT INTO RankCustomer (Point, Description, Name, IsActive)
VALUES (60, 'Gold Rank', 'Gold', true);
INSERT INTO RankCustomer (Point, Description, Name, IsActive)
VALUES (80, 'Diamond Rank', 'Diamond', true);

INSERT INTO Promotion (Percent, Quantity, Type, StartTime, EndTime, Description, Name, IsActive, RankCustomerId)
VALUES (5, 3, 'Bronze', '2024-03-21', '2024-04-22', 'Bronze member', 'coupon 5%', true, 1);
INSERT INTO Promotion (Percent, Quantity, Type, StartTime, EndTime, Description, Name, IsActive, RankCustomerId)
VALUES (10, 2, 'Silver', '2024-03-21', '2024-04-22', 'Silver member', 'coupon 10%', true, 2);
INSERT INTO Promotion (Percent, Quantity, Type, StartTime, EndTime, Description, Name, IsActive, RankCustomerId)
VALUES (15, 1, 'Gold', '2024-03-21', '2024-04-22', 'Gold member', 'coupon 15%', true, 3);
INSERT INTO Promotion (Percent, Quantity, Type, StartTime, EndTime, Description, Name, IsActive, RankCustomerId)
VALUES (20, 5, 'Diamond', '2024-03-21', '2024-04-22', 'Diamond member', 'coupon 20%', true, 4);

INSERT INTO Food (Price, Description, Image, NameOfFood, IsActive)
VALUES (30000, 'Snack', 'src', 'PopCorn', true);
INSERT INTO Food (Price, Description, Image, NameOfFood, IsActive)
VALUES (20000, 'Drink', 'src', 'Pepsi', true);
INSERT INTO Food (Price, Description, Image, NameOfFood, IsActive)
VALUES (20000, 'Drink', 'src', 'Coca', true);
INSERT INTO Food (Price, Description, Image, NameOfFood, IsActive)
VALUES (25000, 'Snack', 'src', 'Chip', true);

INSERT INTO MovieType (MovieTypeName, IsActive)
VALUES ('Comedy', true);
INSERT INTO MovieType (MovieTypeName, IsActive)
VALUES ('Horror', true);
INSERT INTO MovieType (MovieTypeName, IsActive)
VALUES ('Romance', true);
INSERT INTO MovieType (MovieTypeName, IsActive)
VALUES ('Drama', true);
INSERT INTO MovieType (MovieTypeName, IsActive)
VALUES ('Fantasy', true);

INSERT INTO Movie (Description, Director, EndTime, HeroImage, Image, IsActive, Language, MovieDuration, Name,
                   PremiereDate, Trailer, MovieTypeId, RateId, TicketSoldQuantity)
VALUES ('description comedy type', 'Rajkumar Hirani', '2024-04-20', 'src', 'src', true, 'English', 100, '3 idiots',
        '2024-03-23', 'url', 1, 3, 100);
INSERT INTO Movie (Description, Director, EndTime, HeroImage, Image, IsActive, Language, MovieDuration, Name,
                   PremiereDate, Trailer, MovieTypeId, RateId, TicketSoldQuantity)
VALUES ('description horror type', 'James Wan', '2024-04-20', 'src', 'src', true, 'English', 100, 'Insidious',
        '2024-03-23', 'url', 2, 2, 200);
INSERT INTO Movie (Description, Director, EndTime, HeroImage, Image, IsActive, Language, MovieDuration, Name,
                   PremiereDate, Trailer, MovieTypeId, RateId, TicketSoldQuantity)
VALUES ('description fantasy type', 'Christopher Nolan', '2024-04-20', 'src', 'src', true, 'English', 100,
        'Interstellar', '2024-03-23', 'url', 5, 3, 150);
INSERT INTO Movie (Description, Director, EndTime, HeroImage, Image, IsActive, Language, MovieDuration, Name,
                   PremiereDate, Trailer, MovieTypeId, RateId, TicketSoldQuantity)
VALUES ('description fantasy type', 'Ridley Scott', '2024-04-20', 'src', 'src', true, 'English', 100, 'The Martian',
        '2024-03-23', 'url', 5, 2, 76);
INSERT INTO Movie (Description, Director, EndTime, HeroImage, Image, IsActive, Language, MovieDuration, Name,
                   PremiereDate, Trailer, MovieTypeId, RateId, TicketSoldQuantity)
VALUES ('description romance type', 'James Cameron', '2024-04-20', 'src', 'src', true, 'English', 100, 'Titanic',
        '2024-03-23', 'url', 3, 2, 50);

INSERT INTO Cinema (Address, Code, Description, IsActive, NameOfCinema)
VALUES ('3 Nguyen Trai', 'NTC', 'standard cinema', true, 'Nguyen Trai Cinema');
INSERT INTO Cinema (Address, Code, Description, IsActive, NameOfCinema)
VALUES ('4 Le Loi', 'LLC', 'VIP cinema', true, 'Le Loi Cinema');
INSERT INTO Cinema (Address, Code, Description, IsActive, NameOfCinema)
VALUES ('5 Quang Trung', 'QTC', 'standard cinema', true, 'Quang Trung Cinema');

INSERT INTO Room (Type, Capacity, Code, Description, IsActive, Name, CinemaId)
VALUES (100, 50, 'NTC-1', '2D', true, 'Standard Room', 1);
INSERT INTO Room (Type, Capacity, Code, Description, IsActive, Name, CinemaId)
VALUES (100, 50, 'LLC-1', '2D', true, 'VIP Room', 2);
INSERT INTO Room (Type, Capacity, Code, Description, IsActive, Name, CinemaId)
VALUES (100, 50, 'QTC-1', 'imax', true, 'Standard Room', 3);

INSERT INTO Schedule (Code, EndAt, IsActive, Name, Price, StartAt, MovieId, RoomId)
VALUES ('NTC', '2024-03-30', true, 'Earlier', 60000, '2024-03-23', 1, 1);
INSERT INTO Schedule (Code, EndAt, IsActive, Name, Price, StartAt, MovieId, RoomId)
VALUES ('NTC', '2024-03-30', true, 'Earlier', 60000, '2024-03-23', 3, 1);
INSERT INTO Schedule (Code, EndAt, IsActive, Name, Price, StartAt, MovieId, RoomId)
VALUES ('LLC', '2024-03-30', true, 'Earlier', 60000, '2024-03-23', 1, 2);
INSERT INTO Schedule (Code, EndAt, IsActive, Name, Price, StartAt, MovieId, RoomId)
VALUES ('LLC', '2024-03-30', true, 'Earlier', 60000, '2024-03-23', 5, 2);
INSERT INTO Schedule (Code, EndAt, IsActive, Name, Price, StartAt, MovieId, RoomId)
VALUES ('LLC', '2024-04-08', true, 'Standard', 50000, '2024-04-01', 4, 2);
INSERT INTO Schedule (Code, EndAt, IsActive, Name, Price, StartAt, MovieId, RoomId)
VALUES ('QTC', '2024-03-30', true, 'Earlier', 60000, '2024-03-23', 2, 3);

INSERT INTO Seat (Number, IsActive, Line, RoomId, SeatStatusId, SeatTypeId)
VALUES (10, true, 'A', 1, 2, 1);
INSERT INTO Seat (Number, IsActive, Line, RoomId, SeatStatusId, SeatTypeId)
VALUES (10, true, 'A', 2, 2, 2);
INSERT INTO Seat (Number, IsActive, Line, RoomId, SeatStatusId, SeatTypeId)
VALUES (10, true, 'A', 3, 2, 1);

INSERT INTO User (Email, IsActive, Name, Password, PhoneNumber, Point, UserName, RankCustomerId, RoleId, UserStatusId, roleEnums)
VALUES ('20211775@eaut.edu.vn', true, 'Anh', '$2a$12$rvCV5atwDirj9x99mWXJFugT2Ac9jsC/st.9OXs2Lp43fYYV7EzIe', '0912004595', 40, 'user', 2, 3, 2, 'USER');
INSERT INTO User (Email, IsActive, Name, Password, PhoneNumber, Point, UserName, RankCustomerId, RoleId, UserStatusId, roleEnums)
VALUES ('quoctuanstar0610@gmail.com', true, 'Anh', '$2a$12$vkOSajXAVUBtHVfgt2bQvOhBoYTbVvI52EDsbSrFgTy7Zk8fzsFfK', '0912004596', 60, 'user1', 3, 2, 2, 'USER');
INSERT INTO User (Email, IsActive, Name, Password, PhoneNumber, Point, UserName, RankCustomerId, RoleId, UserStatusId, roleEnums)
VALUES ('vanlam@gmail.com', true, 'Lam', '$2a$12$SIEoGIclkU0Fk0E8iNEYW.DiHuEQdIKH/C0NW.i2UWmdMhEiHNLMS', '0912004597', 40, 'user2', 2, 3, 2, 'USER');
INSERT INTO User (Email, IsActive, Name, Password, PhoneNumber, Point, UserName, RankCustomerId, RoleId, UserStatusId, roleEnums)
VALUES ('quoctuanisme0610@gmail.com@gmail.com', true, 'ADMIN', '$2a$12$RdvIn6k3e.T8zL6LB7Y78OP9w5YMwaNKkgcV76AEAzmm4cHaMFC3G', '0912004597', 40, 'admin', 2, 1, 2, 'ADMIN');


# INSERT INTO Bill (CreateTime, IsActive, Name, TotalMoney, TradingCode, UpdateTime, BillStatusId, PromotionId,
#                   CustomerId)
# VALUES ('2024-03-24 18:05:13', true, 'Bill Tran Anh', 148500, 'BTA-2403', '2024-03-24 18:08:13', 2, 2, 1);
# INSERT INTO Bill (CreateTime, IsActive, Name, TotalMoney, TradingCode, UpdateTime, BillStatusId, PromotionId,
#                   CustomerId)
# VALUES ('2024-03-24 17:03:31', true, 'Bill Quoc Anh', 255000, 'QTA-2403', '2024-03-24 17:08:53', 2, 3, 1);
# INSERT INTO Bill (CreateTime, IsActive, Name, TotalMoney, TradingCode, UpdateTime, BillStatusId, PromotionId,
#                   CustomerId)
# VALUES ('2024-03-26 15:08:02', true, 'Bill Tran Anh', 108000, 'BTA-2603', '2024-03-26 15:11:19', 2, 2, 1);
# INSERT INTO Bill (CreateTime, IsActive, Name, TotalMoney, TradingCode, UpdateTime, BillStatusId, PromotionId,
#                   CustomerId)
# VALUES ('2024-03-26 15:08:02', true, 'Bill Van Lam', 216000, 'BVL-2603', '2024-03-26 15:11:19', 2, 2, 3);
#
# INSERT INTO BillFood (Quantity, BillId, FoodId)
# VALUES (1, 1, 3);
# INSERT INTO BillFood (Quantity, BillId, FoodId)
# VALUES (1, 1, 4);
# INSERT INTO BillFood (Quantity, BillId, FoodId)
# VALUES (1, 2, 2);
# INSERT INTO BillFood (Quantity, BillId, FoodId)
# VALUES (2, 2, 3);
# INSERT INTO BillFood (Quantity, BillId, FoodId)
# VALUES (2, 2, 1);
#
# INSERT INTO Ticket (Code, IsActive, PriceTicket, ScheduleId, SeatId)
# VALUES ('NTC-1', true, 60000, 2, 1);
# INSERT INTO Ticket (Code, IsActive, PriceTicket, ScheduleId, SeatId)
# VALUES ('LLC-1', true, 60000, 3, 2);
# INSERT INTO Ticket (Code, IsActive, PriceTicket, ScheduleId, SeatId)
# VALUES ('QTC-1', true, 60000, 6, 3);
# INSERT INTO Ticket (Code, IsActive, PriceTicket, ScheduleId, SeatId)
# VALUES ('NTC-1', true, 60000, 1, 1);
#
# INSERT INTO BillTicket (Quantity, BillId, TicketId)
# VALUES (2, 1, 1);
# INSERT INTO BillTicket (Quantity, BillId, TicketId)
# VALUES (3, 2, 2);
# INSERT INTO BillTicket (Quantity, BillId, TicketId)
# VALUES (2, 3, 3);
# INSERT INTO BillTicket (Quantity, BillId, TicketId)
# VALUES (4, 4, 4);


