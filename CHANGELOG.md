# Changelog

## v4.4.0 (2026-06-21) — Corax-Strata
### Features
- 🎙️ **Edge TTS 语音合成**: `/ai tts on/off` 开关，AI 回复可语音播放
- 🎤 **7 个音色**: 晓晓/晓伊/晓萱（女声），云希/云健/云扬/云夏（男声）
- `/ai tts voice <name>` 一键切换音色（自动补齐 zh-CN-/Neural）
- `/ai tts` 显示音色列表帮助
- TTS 模式下文本与语音同时输出（文字回复 + 语音消息）
- `/help` 增加 TTS 命令说明

### Bug Fixes
- 修复 setAiConfig 函数误删导致音色切换报错
- 修复 BeanShell 孤儿 catch 块解析错误
- 移除 #TEST 调试钩子

### Refactor
- 精简 TTS 链路：Edge TTS → MP3 → sendPtt 直发
- 移除所有 SILK/AMR 编码尝试代码（~730 行）
- 移除 kktts 枫林 TTS 支持
- 移除 plugin/bin/ 二进制文件

### Docs
- 更新帮助文本、音色列表
- 更新版本号至 v4.4.0

## v4.3.2
### Features
- Add set_reminder / cancel_reminder / list_reminders tools: AI can set timed reminders, cancel them, and query pending reminders with remaining time
- Handler.postDelayed precise scheduling, independent of message events
- Add `/ai reminder` command: view own pending reminders with remaining time
- Add `/ai reminder all` command: admins can view all users' pending reminders
- Add `/ai reminder rm <id>` command: cancel a specific reminder
- Add search_memory / search_public_memory tools: AI can search memories by content keyword, not just by tag
- Wake words now work outside listen mode (previously only worked in listen sessions)
- Enhance Tavily Extract (fetch_page): batch-extract up to 5 URLs, advanced depth, 6000 char limit
- fetch_page tool only exposed when search_provider=tavily
- Search final round physically prevents tool calls, ensuring answer output
- Protocol: add <refmsgid> tag explanation, close </s>, SEWarden clean quoted text
- Tag rename: <qid>→<refmsgid>, <mop>→<memop>, <hit>→<tagresult>

### Bug Fixes
- Fix duplicate code blocks: reply-element parsing, reboot/debug guard, overwrite_public_memory double delete+store, canUseAi redundant ADMIN check
- Fix duplicate ctx write: user+R1 was being added to context twice
- Fix fetch_page conditional injection regression (restore upstream #27 behavior)
- Fix callAI duplicate finally block (BeanShell parse error)
- Fix list_reminders Long cast crash (BeanShell incompatible with typed long declarations)
- Fix duplicate keys array declaration in handleAiConfig
- Fix ctx ordering: user+R1 now saved before tool loops
- Fix callAI HTTP connection leak (missing finally disconnect)
- Fix dumpctx: quote extraction moved before export, add SEWarden clean
- Fix overwrite_memory SQL: subquery replaces unsupported ORDER BY LIMIT
- Fix overwrite_memory: preserve original subject_uin on overwrite
- Fix /ai config: show missing keys (search_rounds, pat_wake, sewarden)
- Fix /help version, getMemberName duplicate check, canUseAi double getRole
- Fix onDestroy: save all contexts before clearing
- Fix listen mode: saveCtxToDisk after recording

### Naming Consistency
- Rename searchPublicByTag → searchPublicMemoriesByTag (symmetric with searchMemoriesByTag)
- Rename getPendingReminders → getReminders, getAllPendingReminders → getAllReminders
- Unify reminder tags to singular <reminder> across set/cancel/list

## v4.2
### Features
- Add support for Tavily web search provider
- Allow configurable maximum search rounds
