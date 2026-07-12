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

The key insight: the ontology rarely changes (business concepts are stable), while the physical schema changes frequently. By separating them, you can rename a column or migrate a table without breaking metric definitions or business rules.

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
