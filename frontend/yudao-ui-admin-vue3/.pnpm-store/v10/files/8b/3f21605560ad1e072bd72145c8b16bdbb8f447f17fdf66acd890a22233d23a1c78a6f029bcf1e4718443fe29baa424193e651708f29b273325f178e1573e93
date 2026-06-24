/**
 * @description Format Painter
 * @author CodePencil
 */
import { IButtonMenu, IDomEditor } from '@wangeditor-next/core';
import { Text } from 'slate';
interface FormatPaintAttributes {
    isSelect: boolean;
    formatStyle: Omit<Text, 'text'> | null;
}
declare class FormatPainter implements IButtonMenu {
    title: string;
    iconSvg: string;
    tag: string;
    static attrs: FormatPaintAttributes;
    getValue(_editor: IDomEditor): string | boolean;
    isActive(_editor: IDomEditor): boolean;
    isDisabled(_editor: IDomEditor): boolean;
    setFormatHtml(editor: IDomEditor): void;
    exec(editor: IDomEditor): void;
}
export default FormatPainter;
