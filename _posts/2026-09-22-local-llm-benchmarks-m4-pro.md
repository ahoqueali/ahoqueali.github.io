---
layout: post
title: "Benchmarking Local LLMs on the M4 Pro MacBook Pro"
date: 2026-09-22 09:00:00 +0100
categories: [AI, Machine Learning, Hardware]
tags: [llm, ollama, mlx, benchmarks, m4-pro, local-llms]
author: Ibaadul Hoque
description: "Notes from stress-testing thirteen open-weight models locally through Ollama on a 24GB M4 Pro MacBook Pro — speed, accuracy, RAM fit and which ones are actually worth running."
excerpt: "Finding a local model that is fast, accurate and fits in 24GB of RAM on the M4 Pro is harder than it looks. Qwen3.8, Gemma 4 and Magistral come out on top."
---

Finding a large language model that runs well locally is a game of trade-offs. This is a roundup of notes from stress-testing thirteen open-weight models through [Ollama](https://ollama.com) on a 24 GB MacBook Pro M4 Pro — measuring tokens per second, accuracy, RAM fit, chip saturation and overall sanity.

## Summary

Models are marked with a medal for the standouts: 🎖️ for the winner, 🥈 for the runner-up, and 🥉 for the bronze.

| Model | Speed (tok/s) | Accuracy | Notes |
|-------|--------------|----------|-------|
| Qwen3.8:27b MLX 🎖️ | 22.59 | Very accurate | Fits well, saturates M4 Pro |
| Gemma 4 31B 🥈 | 9.85 | Highly accurate | Fits in 24 GB, needs a custom fan curve |
| Gemma4:12b MLX 🥉 | 39.78 | Above average | Runs slower than the 26b variant |
| Qwen3:14b | 24.54 | Average | Efficient, ~9 GB RAM to spare |
| Cogito:14b | 25.25 | Lacking | Heat/inefficient, not recommended |
| Devstral-small-2:24b | 15.8 | — | Fast enough, but can't count |
| Magistral:24b | 15.75 | Very high detail | Efficient, precision-oriented |
| Mistral-small:24b | 16.39 | Mediocre | Below average efficiency |
| Gemma4 | 55.29 | Pretty accurate | Unaware of the ~4000 token limit |
| Ornith 1.5 35B | 6.78 | Very accurate | Swaps ~8 GB, throttled |
| Granite4.2:30B | — | — | Unresponsive / fails to load |
| Qwen3.6:27b MLX | — | — | Doesn't run (excessive size) |
| North-mini-code-1.0 NVFP4 | — | — | Doesn't run (excessive size) |

## The Medallists

### 🎖️ Qwen3.8:27b MLX variant

- Runs pretty fast at **22.59 tokens per second**
- Very accurate
- Fits very well on RAM
- Saturates the M4 Pro

### 🥈 Gemma 4 31B

- Runs decently fast at **9.85 tokens per second**
- Highly accurate
- Doesn't get cut off like Gemma4 latest
- Fits perfectly into the 24 GB of RAM on the MacBook Pro M4 Pro
- Fully saturates the M4 Pro chip
- Requires a custom fan curve to run at peak performance and efficiency — performance cores hit upwards of **110 °C** under constant heavy load (recommended adjustment with "Mac Fan Control")

### 🥉 Gemma4:12b MLX variant

- Runs pretty fast at **39.78 tokens per second**
- Above average accuracy
- Does not saturate the 24 GB of RAM
- Somehow runs slower than the 26b variant

## Notes by Model

### Qwen3:14b

- Runs pretty fast at **24.54 tokens per second**
- Fits very well on RAM, leaving roughly 9 GB to spare
- Average attention to detail
- Pretty efficient

### Cogito:14b

- Runs pretty fast at **25.25 tokens per second**
- Doesn't spend time to think
- Lacking accuracy / attention to detail
- Fits well on RAM using only 9 GB of RAM*
- Inefficient — the CPU runs hot relative to the tokens generated

\* While in operation, Activity Monitor only saw Ollama use a max of one gigabyte of RAM. Unable to figure out why.

**Model not recommended.**

### Devstral-small-2:24b

- Runs decently fast at **15.8 tokens per second**
- Fits well on RAM
- Can't count — when asked for a 4000-word essay it produced less than 1000 words while claiming to have written roughly 4000
- Efficient-ish

### Magistral:24b

- Runs decently fast at **15.75 tokens per second**
- Very high attention to detail
- Fits very well on RAM
- Pretty efficient

This model is oriented toward doing tasks to the highest degree of accuracy it can muster. Don't believe it? Try saying hello.

### Mistral-small:24b

- Runs decently fast at **16.39 tokens per second**
- Doesn't spend time to think
- Mediocre accuracy / attention to detail
- Fits well on RAM using only 14 GB of RAM*
- Below average efficiency

\* Again, Activity Monitor failed to properly detect the RAM utilisation of the model. This asterisk will appear if the same error shows up in other tests.

### Gemma4

- Runs extremely fast at **55.29 tokens per second**
- Pretty accurate
- Completely unaware of the token limit of around 4000

### Ornith 1.5 35B

- Runs decently at **6.78 tokens per second**
- Very accurate, likes to think a lot
- Saturates all 24 GB of RAM, forcing macOS into roughly 8 GB of swap space
- Unable to fully saturate the M4 Pro chip due to swap usage

Test methodology note: these tests were carried out directly through Ollama in the terminal. Using the desktop application appears to have major adverse effects on performance — the Ollama desktop application seems extremely buggy.

## The Non-Runners

Three models failed to run, mostly due to size:

- **Granite4.2:30B** — unresponsive, fails to load even though it theoretically fits on RAM. Not recommended; use Ornith or something similar.
- **Qwen3.6:27b MLX variant** — doesn't run due to the model's excessive size.
- **North-mini-code-1.0 NVFP4 MLX variant** — doesn't run due to the model's excessive size.

## Takeaways

- **Qwen3.8:27b MLX** is the sweet spot for speed + accuracy + RAM fit on a 24 GB M4 Pro.
- **Gemma 4 31B** is the most accurate heavyweight, but budget for a fan curve if you want sustained peak performance.
- The **Ollama desktop app** noticeably hurts performance versus the terminal — run benchmarks in the terminal.
- Bigger is not better when the machine starts swapping to keep up.