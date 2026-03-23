#!/usr/bin/env python3
from pathlib import Path
import re
import shutil
import sys

FILES = [
    "src/main/java/cd/ethercd/it/ITcFluid.java",
    "src/main/java/cd/ethercd/it/ITcCreativeTab.java",
    "src/main/java/cd/ethercd/it/items/BasicItem.java",
    "src/main/java/cd/ethercd/it/blocks/BasicBlock.java",
]

BUILD_REPLACEMENTS = [
    (r'setTranslationKey\s*\(', 'setUnlocalizedName('),
    (r'\bgetTranslationKey\s*\(', 'getUnlocalizedName('),
    (r'createIcon', 'getTabIconItem')
]

RUNTIME_REPLACEMENTS = [
    (r'setUnlocalizedName\s*\(', 'setTranslationKey('),
    (r'getUnlocalizedName\s*\(', 'getTranslationKey('),
    (r'getTabIconItem', 'createIcon')
]

def patch_file(path_str: str, replacements):
    path = Path(path_str)
    if not path.exists():
        print(f"[skip] not found: {path}")
        return

    original = path.read_text(encoding="utf-8")
    patched = original

    for pattern, repl in replacements:
        patched = re.sub(pattern, repl, patched)

    if patched != original:
        backup = path.with_suffix(path.suffix + ".bak")
        shutil.copy2(path, backup)
        path.write_text(patched, encoding="utf-8")
        print(f"[ok] patched: {path}")
        print(f"     backup: {backup}")
    else:
        print(f"[ok] no changes: {path}")

def main():
    patch_type = sys.argv[1]

    for file in FILES:
        if patch_type == "build":
            patch_file(file, BUILD_REPLACEMENTS)
        if patch_type == "run":
            patch_file(file, RUNTIME_REPLACEMENTS)

if __name__ == "__main__":
    main()