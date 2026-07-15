---
layout: post
title: "Designing Data Systems AI Can Trust: A Three‑Layer Approach"
date: 2026-07-14
tags:
  - AI-ready-data
  - enterprise-architecture
  - ontology
  - semantic-model
  - data-modeling
  - analytics-engineering
  - natural-language-query
  - AI-architecture
  - business-intelligence
  - data-quality
author: A. Hoque Ali
---

I’ve been exploring a practical approach for making enterprise data truly **AI‑ready** — by structuring it into three distinct layers that give AI the context it needs to *reason*, not just *query*.

## 🔹 **Ontology** — defines the core business concepts  
## 🔹 **Semantic Model** — defines how those concepts are measured  
## 🔹 **Data Model** — defines how the data is physically stored

This separation isn’t just good modelling practice — **it’s what makes AI effective**.

---

## Why This Matters for AI‑Ready Systems

Most organisations expect AI to answer business questions directly from natural language. But AI can only do that reliably if it has structured knowledge at each layer:

- **Ontology** — tells AI what business terms mean  
- **Semantic model** — tells AI how to calculate things  
- **Data model** — tells AI where the data lives  

Without these layers, AI is forced to guess — often incorrectly — from raw schemas, inconsistent definitions, or tribal knowledge.

---

## What an AI‑Ready Architecture Enables

When someone asks:

> *“Average monthly revenue from new customers in Europe over the last 12 months”*

An AI‑ready system can:

- Interpret **“new customer”**, **“revenue”**, and **“Europe”** using the **ontology**  
- Map the question to measures, dimensions, filters, and time logic via the **semantic model**  
- Generate accurate SQL using the **data model**  

This means AI can do more than produce a query — it can:

- Explain its reasoning  
- Apply business rules consistently  
- Deliver answers people trust  


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

The full project is on GitHub: [ai-data-system](https://github.com/ahoqueali/ai-data-system)
