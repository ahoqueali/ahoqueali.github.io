# Problem Statement
For most banking apps having the account id in the REST resource url is not allowed.

E.g. /customers/12345/addresses

# Solution
Replace the id with a placeholder like current as used by [Confluence](https://docs.atlassian.com/atlassian-confluence/REST/5.4.4/#d2e90) 

E.g. /customers/current/addresses

Here is a example [swagger spec](https://petstore.swagger.io/?url=https://raw.githubusercontent.com/ahoqueali/ahoqueali.github.io/master/rest/address-openapi.yaml#/Address/getAddress)

# References
1. [Designing URI for current logged in user in REST application](https://stackoverflow.com/questions/36520372/designing-uri-for-current-logged-in-user-in-rest-applications)
