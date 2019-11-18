package com.ktb.leadandsales.line.message.flex.supplier;

import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.linecorp.bot.model.message.FlexMessage;

@Service
public class InquirySummaryFlexMessageSupplier implements Supplier<FlexMessage> {

	@Override
	public FlexMessage get() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
    @Autowired
    BoxBuilderService boxBuilderService;

    @Autowired
    TextBuilderService textBuilderService;

    @Autowired
    IconBuilderService iconBuilderService;

    @Autowired
    SpacerBuilderService spacerBuilderService;

    @Autowired
    ButtonBuilderService buttonBuilderService;

    @Autowired
    SeparatorBuilderService separatorBuilderService;

    private final String NGROK_URI = "https://51c311bc.ngrok.io";

    @Override
    public FlexMessage get() {
        final Image heroBlock = createHeroBlock();
        final Box bodyBlock = createBodyBlock();
        final Box footerBlock = createFooterBlock();

        final Bubble bubbleContainer = Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .footer(footerBlock)
                .build();
        return new FlexMessage("InquirySummary", bubbleContainer);
    }

    public Image createHeroBlock() {
        URI uriImage = URI.create(NGROK_URI + "/images/01");
        URI uriExample = URI.create("http://www.iamnavanit.com");

        return Image.builder()
                .url(uriImage)
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectRatio(Image.ImageAspectRatio.R20TO13)
                .aspectMode(Image.ImageAspectMode.Cover)
                .action(new URIAction("label", uriExample, null)) //URIAction.AltUri
                .build();
    }

    public Box createBodyBlock() {
        final Text title = Text.builder()
                .text("Insure Tech")
                .weight(Text.TextWeight.BOLD)
                .size(FlexFontSize.XL)
                .build();

        final Box review = createReviewBox();
        final Box info = createInfoBox();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(asList(title, review, info))
                .build();
    }

    public Box createFooterBlock() {
        final Spacer spacer = spacerBuilderService.create(new SpacerBuilderDto(FlexMarginSize.SM));

        final Button callAction = buttonBuilderService.create(new ButtonBuilderDto(
                Button.ButtonStyle.LINK, Button.ButtonHeight.MEDIUM,
                new URIAction("CALL", URI.create("tel:00000"), null)
        ));

        final Separator separator = separatorBuilderService.create(new SeparatorBuilderDto());

        final Button websiteAction = buttonBuilderService.create(new ButtonBuilderDto(
                Button.ButtonStyle.LINK, Button.ButtonHeight.SMALL,
                new URIAction("WEBSITE", URI.create("http://www.iamnavanit.com"), null)
        ));

        List contents = asList(spacer, callAction, separator, websiteAction);

        return boxBuilderService.create(
                new BoxBuilderDto(FlexLayout.VERTICAL, FlexMarginSize.SM, contents)
        );
    }

    private Box createInfoBox() {
        List contents = asList(
                textBuilderService.create(new TextBuilderDto("Place", "#aaaaaa", FlexFontSize.SM,1)),
                textBuilderService.create(new TextBuilderDto("Somewhere, Bangkok", "#666666", FlexFontSize.SM,5)
        ));
        final Box place = boxBuilderService.create(
            new BoxBuilderDto(FlexLayout.BASELINE, FlexMarginSize.SM, contents)
        );

        contents = asList(
                textBuilderService.create(new TextBuilderDto("Time", "#aaaaaa", FlexFontSize.SM,1)),
                textBuilderService.create(new TextBuilderDto("10:00 - 23:00", "#666666", FlexFontSize.SM,5)
                        ));

        final Box time = boxBuilderService.create(
            new BoxBuilderDto(FlexLayout.BASELINE, FlexMarginSize.SM, contents)
        );

        return boxBuilderService.create(
            new BoxBuilderDto(FlexLayout.VERTICAL, FlexMarginSize.LG, FlexMarginSize.SM, asList(place, time))
        );
    }

    private Box createReviewBox() {
        final Icon goldStar = iconBuilderService.create(
                new IconBuilderDto(FlexFontSize.SM, NGROK_URI + "/images/gold_star")
        );

        final Icon grayStar = iconBuilderService.create(
                new IconBuilderDto(FlexFontSize.SM, NGROK_URI + "/images/gray_star")
        );

        final Text point = textBuilderService.create(
                new TextBuilderDto("4.0", FlexMarginSize.MD, "#999999", FlexFontSize.SM, 0)
        );

        List contents = asList(goldStar, goldStar, goldStar, goldStar, grayStar, point);
        return boxBuilderService.create(
                new BoxBuilderDto(FlexLayout.BASELINE, FlexMarginSize.MD, contents)
        );
    }
    */

}
