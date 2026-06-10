/**
 * @description image menu entry
 * @author wangfupeng
 */
import DeleteImage from './DeleteImage';
import EditImage from './EditImage';
import EditorImageSizeMenu from './EditImageSizeMenu';
import InsertImage from './InsertImage';
import ViewImageLink from './ViewImageLink';
import ImageWidth30 from './Width30';
import ImageWidth50 from './Width50';
import ImageWidth100 from './Width100';
export declare const insertImageMenuConf: {
    key: string;
    factory(): InsertImage;
    config: {
        onInsertedImage(_imageElem: import("../custom-types").ImageElement): void;
        onUpdatedImage(_node: import("../custom-types").ImageElement | null): void;
        checkImage(_src: string, _alt: string, _href: string): boolean | string | undefined;
        parseImageSrc(src: string): string;
    };
};
export declare const deleteImageMenuConf: {
    key: string;
    factory(): DeleteImage;
};
export declare const editImageMenuConf: {
    key: string;
    factory(): EditImage;
    config: {
        onInsertedImage(_imageElem: import("../custom-types").ImageElement): void;
        onUpdatedImage(_node: import("../custom-types").ImageElement | null): void;
        checkImage(_src: string, _alt: string, _href: string): boolean | string | undefined;
        parseImageSrc(src: string): string;
    };
};
export declare const viewImageLinkMenuConf: {
    key: string;
    factory(): ViewImageLink;
};
export declare const imageWidth30MenuConf: {
    key: string;
    factory(): ImageWidth30;
};
export declare const imageWidth50MenuConf: {
    key: string;
    factory(): ImageWidth50;
};
export declare const imageWidth100MenuConf: {
    key: string;
    factory(): ImageWidth100;
};
export declare const EditorImageSizeMenuConf: {
    key: string;
    factory(): EditorImageSizeMenu;
};
