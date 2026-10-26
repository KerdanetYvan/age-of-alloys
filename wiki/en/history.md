---
layout: default
title: History
permalink: /en/history/
alt: /historique/
lang: en
key: history
---

# Version history

The news of every version, the most recent first. The technical detail is in the [CHANGELOG](https://github.com/KerdanetYvan/age-of-alloys/blob/main/CHANGELOG.md) of the repository (in French).

{% for entry in site.data.news %}
{%- assign vid = entry[0] -%}
{%- assign known = site.data.versions | where: "id", vid | first %}
## {{ vid }} <small>{{ entry[1].date }}</small> {#{{ vid | replace: '.', '-' }}}

{% include news.html version=vid %}
{%- if known %}
[The documentation of {{ vid }}]({{ '/en/' | append: vid | append: '/' | relative_url }})
{%- endif %}
{% endfor %}
