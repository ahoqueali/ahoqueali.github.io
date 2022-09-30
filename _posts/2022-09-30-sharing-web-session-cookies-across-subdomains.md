---
layout: post
title: "Sharing web session cookies across subdomains"
date: 2022-09-30 00:00:00 -0000
categories: BLOG
---

# Problem Statement
How to share session cookies across subdomains

If cookie domain attribute is set to 
    subdomain.superdomain.co.uk
 
then cookies can be shared across sub domains like
    subsubdomain.subdomain.superdomain.co.uk
 
cookies cannot be shared across sub domains like
    otherdomain.superdomain.co.uk
                
# References
1. [Sharing cookies](https://stackoverflow.com/questions/18492576/share-cookie-between-subdomain-and-domain)
