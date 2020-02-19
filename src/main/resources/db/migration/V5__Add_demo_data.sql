insert into usr (id, username, password, active, email)
values
  (100, 'realDonaldTrump', '123', true, 'donald@mail.com'),
  (101, 'elonmusk', '123', true, 'elonmusk@mail.com'),
  (102, 'martinfowler', '123', true, 'martinfowler@mail.com');

insert into user_role (user_id, roles)
values (100, 'USER'), (101, 'USER'), (102, 'USER');

insert into message (id, tag, text, user_id)
values
  (1000, 'donald', 'Congratulations to Eddie DeBartolo Jr. and your wonderful family of friends!', 100),
  (1001, 'cry', 'I’m in tears 😂', 100),
  (1002, 'donaldsay', '...And this despite Fake Witch Hunts, the Mueller Scam, the Impeachment Hoax etc. With our Economy, Jobs, Military, Vets, 2A & more, I would be at 70%. Oh well, what can you do?', 100),
  (1003, 'marty', 'I''m too tired to do more writing, but not too tired for some serious refactoring.', 102),
  (1004, 'marty', 'I recently ran into the card game The Crew, and really liked it. More thoughts at', 102),
  (1005, 'marty', 'post: a brief bliki entry on the difference between outcome and output - and what that''s important for software development (amongst other things)', 102),
  (1006, 'donaldsay', 'THANK YOU!', 100),
  (1007, 'spaceX', 'Successful deployment of 60 Starlink satellites confirmed!', 101),
  (1008, 'hope', 'I hope the Federal Judges Association will discuss the tremendous FISA Court abuse that has taken place with respect to the Mueller Investigation Scam, including the forging of documents and knowingly using the fake and totally discredited Dossier before the Court. Thank you!', 100),
  (1009, 'minimike', 'Mini Mike. No, I would rather run against you!', 100),
  (1010, 'minimike', 'What Mini Mike is doing is nothing less than a large scale illegal campaign contribution. He is “spreading” money all over the place, only to have recipients of his cash payments, many former opponents, happily joining or supporting his campaign. Isn’t that called a payoff? .....', 100),
  (1011, 'minimike', '.....Mini is illegally buying the Democrat Nomination. They are taking it away from Bernie again. Mini Mike, Major Party Nominations are not for sale! Good luck in the debate tomorrow night and remember, no standing on boxes!', 100),
  (1012, 'Jacobs', 'Chris Jacobs will be a great Congressman who will always fight for the people of New York. He supports our #MAGA Agenda, will continue to Secure Our Border, Loves our Military, Vets, and is Strong on the #2A. Chris has my Complete Endorsement for the Special Election on 4/28!', 100),
  (1013, 'donaldsay', 'The Mayor’s efforts to shield illegal aliens endangers the lives of the public and law enforcement who have to go into the field to apprehend those released. He shouldn’t be urging illegals to beat the system, he should be urging them to safely turn themselves in!', 100),
  (1014, 'donaldsay', 'Building a border wall system, at one time, was very bipartisan and nonpolitical. It''s only now that some are choosing politics over security. This administration will continue to use existing resources and authorities to get the job done.', 100),
  (1015, 'marty', 'Lots of managers think that to get things done you need to create a sense of urgency. ', 102),
  (1016, 'donaldsay', '“Judge Jackson now has a request for a new trial based on the unambiguous & self outed bias of the foreperson of the jury, whose also a lawyer, by the way. ‘Madam foreperson, you’re a lawyer, you have a duty, an affirmative obligation, to reveal to us when we selected you the....', 100),
  (1017, 'donaldsay', '.....existence of these tweets in which you were so harshly negative about the President & the people who support him. Don’t you think we wanted to know that before we put you on this jury.’ Pretty obvious he should (get a new trial). I think almost any judge in the Country.....', 100),
  (1018, 'spaceX', 'Liftoff!', 101),
  (1019, 'donaldsay', '.....would order a new trial, I’m not so sure about Judge Jackson, I don’t know.” ', 100),
  (1020, 'donaldsay', 'The United States cannot, & will not, become such a difficult place to deal with in terms of foreign countries buying our product, including for the always used National Security excuse, that our companies will be forced to leave in order to remain competitive. We want to sell...', 100),
  (1021, 'tesla', 'Looking forward to international expansion later this year 😀', 101),
  (1022, 'spaceX', 'SpaceX designs, manufactures and launches the world’s most advanced rockets and spacecraft', 101),
  (1023, 'tesla', 'Tesla Model S estimated EPA range is now above 390 miles or ~630 km', 101),
  (1024, 'donaldsay', '....I have seen some of the regulations being circulated, including those being contemplated by Congress, and they are ridiculous. I want to make it EASY to do business with the United States, not difficult. Everyone in my Administration is being so instructed, with no excuses...', 100),
  (1025, 'donaldsay', '....THE UNITED STATES IS OPEN FOR BUSINESS!', 100),
  (1026, 'spaceX', 'Crew Dragon completes acoustic testing in Florida', 101),
  (1027, 'tesla', 'Tesla is ramping up Solar Roof installation across the USA! Training will be provided, so no prior experience needed. Apply at', 101),
  (1028, 'tesla', 'All S/X cars made in recent months have actually been above stated EPA range. Will be unlocked soon for free via software update.', 101),
  (1029, 'tesla', 'Run a physics sim long enough & you’ll get intelligence', 101),
  (1030, 'tesla', 'Only the heart senses beauty', 101),
  (1031, 'marty',
   'Got a briefing yesterday from our folks in China. My thoughts go out to my colleagues and readers over there coping with the coronavirus crisis.',
   102),
  (1032, 'marty',
   '"What requires more explanation is why Wacom think it’s acceptable to record every time I open a new application, including the time, a string that presumably uniquely identifies me, and the application’s name."',
   102);

insert into user_subscriptions (channel_id, subscriber_id)
values
  (100, 101),
  (100, 102),
  (101, 102),
  (101, 100),
  (102, 100);

insert into message_likes (message_id, user_id)
values
  (1002, 100),
  (1003, 100),
  (1007, 100),
  (1018, 100),
  (1000, 100),
  (1002, 101),
  (1003, 102),
  (1007, 101),
  (1015, 102),
  (1018, 102),
  (1015, 100),
  (1016, 100),
  (1017, 100),
  (1021, 100),
  (1019, 100),
  (1020, 100),
  (1026, 100),
  (1027, 100),
  (1028, 100),
  (1029, 100),
  (1030, 100),
  (1031, 100);