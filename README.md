# Spring GraphQL Sandbox

Study project creating a GraphQL API with Spring Boot.


## Pagination

GraphQL has a special — and native — Pagination model. The Relay/Cursor Pattern is usually useful for infinite scroll features and for paginating data with constant updates.

![GraphQL Pagination](images/graphql-pagination.png)

![GraphQL Request](images/graphql-request.png)

## Schema

[» File](/src/main/resources/graphql/schema.graphqls)

```graphql
type Query {
  authors: [Author]
  authorById(id: ID!): Author
  pagedAuthors(first: Int, after: String): AuthorConnection!
  books: [Book]
  bookById(id: ID!): Book
}

type Author {
  id: ID!
  name: String!
  bio: String
  books: [Book]
}

type Book {
  id: ID!
  title: String!
  description: String
  author: Author
}

type AuthorConnection {
  edges: [AuthorEdge!]!
  pageInfo: PageInfo!
}

type AuthorEdge {
  cursor: String!
  node: Author!
}

type PageInfo {
  hasPreviousPage: Boolean!
  hasNextPage: Boolean!
  startCursor: String
  endCursor: String
}

type Mutation {
  createBook(book: BookInput!): Book
} 

input BookInput {
  title: String!
  description: String
  authorId: ID!
}
```