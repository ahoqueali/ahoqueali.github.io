# REST Richardson Maturity Model

| Level | Name  | Description  |
|:----:|--------------|----------|
|  0 | The swamp of POX | Use HTTP as a transport system for remote interactions, but without using any of the mechanisms of the web |
|  1 |   Resources | Make requests to a Resource | 
|  2 | HTTP Verbs | Use HTTP Verbs | 
|  3 | Hypermedia Controls  | Use HATEOAS (Hypertext As The Engine Of Application State). It addresses the question of how to get from a collection of resources to a single resource  |


# REST API Naming Convention

| Resource Type | Description  | Example  |
|:----:|--------------|----------|
|  Collection | The collection resource is a set of singular resources| /customers  |
|  Document |   The document is a singular resource e.g. A customer| /customers/{id} | 
|  Store | The Store resource is a client managed resource | /users/{id}/playlists  | 
|  Controller | The Controller resource models a procedural concept  | /customers/{id}/basket/checkout  |


# Reference
[REST RMM](https://martinfowler.com/articles/richardsonMaturityModel.html)
[REST API Tutorial - Resource naming](https://restfulapi.net/resource-naming/)
