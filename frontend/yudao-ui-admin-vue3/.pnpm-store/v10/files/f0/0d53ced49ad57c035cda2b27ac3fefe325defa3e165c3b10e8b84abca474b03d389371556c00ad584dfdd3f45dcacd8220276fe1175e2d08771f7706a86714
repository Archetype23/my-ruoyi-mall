import { Element, NodeEntry } from 'slate';
export type CellElement = WithType<{
    rowSpan?: number;
    colSpan?: number;
    hidden?: boolean;
} & Element>;
/** Extends an element with the "type" property  */
export type WithType<T extends Element> = T & Record<'type', unknown>;
export type NodeEntryWithContext = [
    NodeEntry<CellElement>,
    {
        rtl: number;
        ltr: number;
        ttb: number;
        btt: number;
    }
];
export type SelectionMode = 'start' | 'end' | 'all';
export type Edge = 'start' | 'end' | 'top' | 'bottom';
