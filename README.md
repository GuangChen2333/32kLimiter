32kLimiter
---
A spigot plugin for limiting 32k weapons

Supported minecraft version: 1.12.2

> Warning: In 2.0.0 and later versions, the plugin need the dependency `NBT API`

> You can download it [Here](https://www.spigotmc.org/resources/nbt-api.7939/)
---

## Commands
| Command | Permission | Description |
| --- | --- | --- |
| /32klimiter | 32klimiter.admin | 32k limiter commands |

### /32klimiter
usage: /32klimiter enable|disable

#### /32klimiter enable
Enable plugin (temporarily)

#### /32klimiter disable
Disable plugin (temporarily)

## Config
```yaml
# Plugin switch
enabled: true
```

## Recent roadmap
- [x] Use NBT to determine whether the item should be tested
- [x] Delete debug mode