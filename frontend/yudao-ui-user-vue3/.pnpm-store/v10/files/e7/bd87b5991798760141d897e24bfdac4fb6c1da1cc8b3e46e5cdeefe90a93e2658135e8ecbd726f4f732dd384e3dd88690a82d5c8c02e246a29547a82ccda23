/**
 * @description parse html
 * @author wangfupeng
 */
import { IDomEditor } from '@wangeditor-next/core';
import { Descendant } from 'slate';
import { DOMElement } from '../utils/dom';
import { TableCellElement, TableElement, TableRowElement } from './custom-types';
declare function parseCellHtml(elem: DOMElement, children: Descendant[], editor: IDomEditor): TableCellElement;
export declare const parseCellHtmlConf: {
    selector: string;
    parseElemHtml: typeof parseCellHtml;
};
declare function parseRowHtml(elem: DOMElement, children: Descendant[], _editor: IDomEditor): TableRowElement;
export declare const parseRowHtmlConf: {
    selector: string;
    parseElemHtml: typeof parseRowHtml;
};
declare function parseTableHtml(elem: DOMElement, children: Descendant[], _editor: IDomEditor): TableElement;
export declare const parseTableHtmlConf: {
    selector: string;
    parseElemHtml: typeof parseTableHtml;
};
export {};
