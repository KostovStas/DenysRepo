Narrative:
In order to perform search correctly
As a user
I want to be able to see the list of found hotels, located in NY


Scenario: User should have possibility to see the list of found hotels, using own searching criterias

Given user has opened 'Booking.com' site
When user performs hotels search for current month, using following criterias:
| destination                   | checkInDate      | checkOutDate      |
| New York, New York State, USA | December 2016, 5 | December 2016, 10 |
Then user shuold see following found hotels:
| hotelName                                             | hotelLocation                  |
| Radisson Martinique on Broadway                       | New York City                  |
| The Bernic Hotel                                      | Midtown East, New York City    |
| 1 Hotel Central Park                                  | New York City                  |
| Park Lane Hotel on Central Park                       | New York City                  |
| The London NYC                                        | New York City                  |
| The Quin                                              | New York City                  |
| The Knickerbocker                                     | New York City                  |
| Courtyard by Marriott New York Manhattan/Central Park | New York City                  |
| Novotel New York Times Square                         | New York City                  |
| Hyatt Herald Square Midtown                           | New York City                  |
| Dream Midtown                                         | New York City                  |
| Heritage Hotel New York City                          | New York City                  |
| Gardens NYC-an Affinia hotel                          | New York City                  |
| AKA Central Park                                      | New York City                  |
| Wellington Hotel                                      | New York City                  |
| Hotel Edison                                          | New York City                  |
| Hotel Belleclaire                                     | Upper West Side, New York City |
| Hudson New York, Central Park                         | Hell's Kitchen, New York City  |
| Hudson Central Park, A Morgans Hotel                  | Hell's Kitchen, New York City  |
| Night Hotel Theater District, Times Square            | New York City                  |
| The Mark New York                                     | Upper East Side, New York City |
| Days Hotel Broadway at 94th Street                    | Upper West Side, New York City |
