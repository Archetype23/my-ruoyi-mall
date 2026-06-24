import { Editor, Element, Node, NodeMatch } from 'slate';
import { WithTableOptions } from './options';
import { WithType } from './types';
export declare function isElement<T extends Element>(node: Node): node is WithType<T>;
/** @returns a `NodeMatch` function which is used to match the elements of a specific `type`. */
export declare function isOfType<T extends WithType<Element>>(editor: Editor, ...types: Array<keyof WithTableOptions['blocks']>): NodeMatch<T>;
