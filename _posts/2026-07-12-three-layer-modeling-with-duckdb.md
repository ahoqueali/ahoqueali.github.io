---
layout: post
title: "Three-Layer Modeling with DuckDB and AI"
date: 2026-07-12
description: "A working example of ontology, semantic, and physical modeling layers — all in YAML, all querying DuckDB."
tags: [olap, AI, ontology, semantic layer, Duck db]
author: A. Hoque Ali
---

I've been experimenting with separating the concerns of data modeling into distinct layers, each answering a different question:

| Layer | Question |
|---|---|s
| **Ontology** | What *is* a Customer, an Account — and how do they relate? |
| **Semantic model** | What metrics and dimensions can I query? |
| **Data model** | What tables, columns, and types physically exist? |


The **ontology** captures stable business concepts and their relationships. It provides the shared language of the business.

The **semantic model** defines how those concepts are measured and analysed—metrics, dimensions, calculations, and business rules.

The **data model** represents the physical implementation—the tables, columns, keys, and data types that store the information.

Keeping these concerns separate makes systems easier to evolve, maintain, and govern. A change to physical storage doesn't have to change business definitions, and new metrics can be introduced without redefining the underlying business concepts.

This separation also becomes increasingly valuable in the age of AI.

When someone asks a question in natural language—*"What was the average monthly revenue from new customers in Europe over the last 12 months?"*—an AI agent has to reason through several layers:

1. **Understand the intent** using the ontology: What does "new customer" mean? What is "revenue"? What does "Europe" represent?
2. **Translate the business question** using the semantic model: Which measures, dimensions, filters, and time calculations correspond to the user's request?
3. **Generate an executable query** using the data model: Which tables should be joined? Which columns contain the required data? What SQL should be produced?

Rather than asking AI to infer everything from a database schema alone, we give it structured knowledge at each level. The ontology provides meaning, the semantic model provides business logic, and the data model provides the implementation details.

The result is AI that can do more than generate SQL—it can **reason about the business question**, map business terminology to enterprise knowledge, explain *why* it produced a particular query, and produce more accurate, trustworthy answers.

This idea feels increasingly important as AI becomes the primary interface to enterprise data. The quality of AI-generated answers will depend as much on the quality of the semantic and ontological layers as it does on the underlying data itself.



## The stack

Everything is defined in YAML. A Python script compiles the data model into DuckDB DDL, auto-seeds a local database, and an MCP server exposes it to [opencode](https://opencode.ai) for natural language querying.

```
ontology/           <- entities, relationships, business rules
semantic_models/    <- dimensions, measures, metrics (dbt/MetricFlow style)
data_model/         <- DuckDB table schemas
```

## Why DuckDB?

Zero infrastructure. No cloud account, no credentials, no throughput billing. BigQuery charges $5 per TB scanned — without careful partitioning, ad-hoc exploration gets expensive fast. DuckDB runs entirely on your machine for free.

## Querying with AI

The [opencode](https://opencode.ai) config in the repo wires up a DuckDB MCP server automatically. Ask a question like "What's the total outstanding mortgage balance by region?" and the LLM reads the three layers to construct correct SQL — not just guessing at column names.

The full project is on GitHub: [opencode-ai-duckdb-olap-reporting](https://github.com/ahoqueali/opencode-ai-duckdb-olap-reporting)
