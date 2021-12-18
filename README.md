32kLimiter
---
A spigot plugin for limiting 32k weapons

Supported minecraft version: 1.12.2

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
# Debug Mode (print more logs)
# Actually I used it myself, not practical
debug_mode: false
```

## Recent roadmap
- [x] Debug Mode
- [x] ~~Fix InventoryClickEvent~~ Listen **InventoryCloseEvent**
- [x] Fix the problem that Shulker Box will be detected
- [ ] Add the list of controlled items