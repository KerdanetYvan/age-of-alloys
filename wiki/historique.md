---
layout: default
title: Historique
permalink: /historique/
alt: /en/history/
lang: fr
key: history
---

# L'historique des versions

Les news de toutes les versions, la plus récente d'abord. Le détail technique est dans le [CHANGELOG](https://github.com/KerdanetYvan/age-of-alloys/blob/main/CHANGELOG.md) du dépôt.

{% for entry in site.data.news %}
{%- assign vid = entry[0] -%}
{%- assign known = site.data.versions | where: "id", vid | first %}
## {{ vid }} <small>{{ entry[1].date }}</small> {#{{ vid | replace: '.', '-' }}}

{% include news.html version=vid %}
{%- if known %}
[La documentation de {{ vid }}]({{ '/' | append: vid | append: '/' | relative_url }})
{%- endif %}
{% endfor %}
