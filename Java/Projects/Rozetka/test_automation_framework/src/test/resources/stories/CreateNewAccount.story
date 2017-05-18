Narrative:
In order to register myself on site
As a user
I want to be able to create & active new account


Scenario: Create and activate new account via UI interface

Given user opens home page
And user opens registration page
When user performs registration, using following user's information:
| userName        | login | password                    |
| RozetkaTestUser | email | RozetkaTempUserPassword2017 |
And user <email> activates his account, using activation link received from email
Then user <userName> should be logged in

Examples:
| email                |
| rozetkatest@binka.me |
