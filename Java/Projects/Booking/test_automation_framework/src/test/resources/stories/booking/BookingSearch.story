Narrative:
In order to perform search correctly
As a user
I want to be able to see the list of found hotels, located in NY


Scenario: User should have possibility to see the list of found hotels, using own searching criterias

Given user has opened 'Booking.com' site
When user performs hotels search for current month, using following criterias:
| destination | checkInDate       | checkOutDate      |
| NY          | December 2016, 10 | December 2016, 15 |
Then user should see found hotels, located in 'New York'

