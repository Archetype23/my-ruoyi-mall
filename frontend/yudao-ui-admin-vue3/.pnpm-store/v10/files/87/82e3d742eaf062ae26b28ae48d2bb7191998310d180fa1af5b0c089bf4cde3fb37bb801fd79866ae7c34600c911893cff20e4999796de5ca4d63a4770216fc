import { IDomEditor } from '@wangeditor-next/core';
import { Element as SlateElement } from 'slate';
/** *
 * 用于计算拖动 cell 时，cell 宽度变化的比例
 */
export declare function getColumnWidthRatios(columnWidths: number[]): number[];
export declare function observerTableResize(editor: IDomEditor, elm: Node | undefined): void;
export declare function unObserveTableResize(): void;
/**
 * 鼠标移动时，判断在哪个 Cell border 上
 * Class 先 visible 后 highlight @跟随飞书
 * 避免光标选区功能收到干扰
 */
export declare function handleCellBorderVisible(editor: IDomEditor, elemNode: SlateElement, e: MouseEvent, scrollWidth: number): void;
/**
 * 设置 class highlight
 * 将 render-cell.tsx 拖动功能迁移至 div.column-resize
 */
export declare function handleCellBorderHighlight(editor: IDomEditor, e: MouseEvent): void;
export declare function handleCellBorderMouseDown(editor: IDomEditor, _elemNode: SlateElement): void;
