// Animation Code
function isItemInViewport(selector) {
    var bounds = selector.offset();

    var top = bounds.top;
    var left = bounds.left;
    var bottom = top + selector.outerHeight();
    var right = left + selector.outerWidth();

    var w = $(window);

    return ((right >= document.body.scrollLeft &&
        left <= document.body.scrollLeft + $(window).width()) &&
        (bottom >= document.body.scrollTop &&
        top <= document.body.scrollTop + $(window).height()))
}

let elementAnimated = {
    '.hero__text': false,
    '.welcome_animate': false,
    '.cuisine_animate': false,
    '.ingredients_animate': false,
    '.events_animate': false,
    '.contacts_animate': false,
    '.table_animate': false,
    '.footer_animate': false,
    '.evt_tgt': false,
};

$(window).scroll(function() {
    for (var k in elementAnimated) {
        let inViewport = isItemInViewport($(k));

        if (elementAnimated[k] !== inViewport) {
            if (inViewport) { // open animation
                $(k).animate({ opacity: 1 }, 700);
            } else { // closing animation
                $(k).animate({ opacity: 0 }, 700);
            }

            elementAnimated[k] = inViewport;
        }
    }

});

// Menu Code
let menuSection = -1;
let recipeData = [
    [
        ['media/pic01.jpg', 'Bruschette with Tomatoes', 'Bruschetta is an antipasto (starter dish) from Italy consisting of grilled bread rubbed with garlic and topped with olive oil and salt. Variations may include toppings of tomato, vegetables, beans, cured meat, or cheese.'],
        ['media/pic02.jpg', 'Green Rolls', 'Chilled, these beauties make a refreshing snack. You\'ll find yourself whipping them up whether you are cleansing or not!'],
        ['media/pic03.jpg', 'Eggplants', 'Eggplant (Solanum melongena), or aubergine, is a species of nightshade grown for its edible fruit.'],
        ['media/pic04.jpg', 'Bruschette', 'Bruschetta is an antipasto (starter dish) from Italy consisting of grilled bread rubbed with garlic and topped with olive oil and salt. Variations may include toppings of tomato, vegetables, beans, cured meat, or cheese.'],
        ['media/pic05.jpg', 'Meatballs', 'A meatball is ground or minced meat rolled into a small ball, sometimes along with other ingredients, such as bread crumbs, minced onion, eggs, butter, and seasoning.'],
        ['media/pic06.jpg', 'Spicy Beans', 'A bit of heat from red chili flakes and plenty of garlic flavor come through with these crunchy, dill-scented green beans.'],
    ],
    [
        ['media/pic11.jpg', 'Carbonara Cake', 'This is a delightfully simple, luxurious, yet slightly trashy leftover meal that is sure to wow everyone. It gives you classic carbonara flavours, but with a crisp exterior to contrast with that creamy interior. Total comfort heaven.'],
        ['media/pic12.jpg', 'Mushroom and Lentil', 'A veg-packed winter warmer.'],
        ['media/pic13.jpg', 'Squash and Ricotta Ravioli', 'Super-creamy ricotta is a great source of the minerals calcium and phosphorus, both of which are essential for healthy bones and teeth, plus it’s lower in fat than most other cheeses.'],
        ['media/pic14.jpg', 'Spinach Pici Pasta', 'Vibrant homemade pasta that\'s super-fun to make and packed with nutritious spinach.'],
        ['media/pic15.jpg', 'Mushroom & Cauliflower Penne', 'Creamy and comforting, with a hit of chilli and lots of lovely herbs to liven it up, this pasta dish is super-satisfying.'],
        ['media/pic16.jpg', 'Tomato & Caper Linguine', 'Tomatoes. Tomatoes. Tomatoes. What else could you need?'],
    ],
    [
        ['media/pic21.jpg', 'Smoked Mackerel & Red Quinoa Patties', 'These flavour-packed fishcakes are lovely as they are, but even better served with a poached egg and a green salad. The red quinoa looks great, but if you only have white quinoa to hand, that will be fine, too.'],
        ['media/pic22.jpg', 'Gravadlax', 'A firm favourite from my travels, this beautiful, delicate dish sums up everything I love about Swedish food: it’s elegant, clean and fresh, it looks incredible and is a doddle to make. You’ll be so proud when you see the finished result.'],
        ['media/pic23.jpg', 'Fish Pie', 'Christmas in the UK, if the weather’s not too rough for the fishermen to go out, can and normally does bring us the most wonderful bounty of gorgeous fish, from cold, clean glorious waters, and this is a classic fish pie recipe that celebrates them all. '],
        ['media/pic24.jpg', 'Fish Cakes', 'The best of British meets Indian spice in this tasty breakfast dish – great at any time of day.'],
        ['media/pic25.jpg', 'Whitebait & Dill Mayo', 'Fish and mayo. What else could one ask for? Yum!'],
        ['media/pic26.jpg', 'Salmon & Pesto-Dressed Veg', 'Salmon is a great source of both omega 3, which helps to keep our hearts healthy, and vitamin D that helps to make our bones and teeth strong. Panfrying salmon is an easy way to get more oily fish in our diets – served with delicious, pesto-dressed potatoes and greens, it’s a great weeknight dinner.'],
    ],
    [
        ['media/pic31.jpg', 'Chocolate Candy Cane Cookies', 'Minty chocolate cookies speckled with crushed candy canes make tempting tree ornaments – you\'ll just have to hope there\'ll be some left on Christmas Day!'],
        ['media/pic32.jpg', 'White Chocalate and Cranberry Cookies', 'Cranberries. Mmmmmmm.'],
        ['media/pic33.jpg', 'DIY Cookie Jar', 'This makes a great present: in a pretty jar, layer dry ingredients for cookie or cake recipes. Tie on a card with instructions on how to turn it all into delicious baked goods.'],
        ['media/pic34.jpg', 'Everything Cookies', 'These chewy cookies, divvied out with an ice cream scoop, make the perfect receptacle for leftover nuts, dried fruit, toffees, chocolate and any other treats you might have hanging about. Vary the additions as you wish – just keep the total quantity to around 150g. If you prefer a crisper cookie, cook them for a couple more minutes. '],
        ['media/pic35.jpg', 'Jools’ Easy Oaty Fruit Cookies', 'A really delicious, simple, comforting cookie – porridge oats are a high-fibre carbohydrate, which means they’ll keep you feeling fuller for longer. Feel free to use whichever dried fruit you like in the mix – things like prunes, apricots, sour cherries and blueberries all work well.'],
        ['media/pic36.jpg', 'Italian-Style Bakewell Tart', 'It\'s a tart. What else could you want...'],
    ],
];

function changeMenuSection(idx) {
    if (idx === menuSection) return;

    menuSection = idx;

    $('.menu__button').removeClass('menu__button--active').eq(idx).addClass('menu__button--active');
    $('.menu__images').hide().eq(idx).show();
}

$(window).scroll(function() {
    if (isItemInViewport($('.menu__header'))) {
        let heightPct = (document.body.scrollTop - $('.menu__header').offset().top) / $('.menu__header').height() * 100;

        if (heightPct <= 20.0) changeMenuSection(0);
        else if (heightPct <= 35.0) changeMenuSection(1);
        else if (heightPct <= 50.0) changeMenuSection(2);
        else if (heightPct <= 65.0) changeMenuSection(3);
    }
});

recipeData.forEach(function (items, idx) {
    var $menu = $('<div class="row menu__images" />').appendTo('.menu__images-outer').hide();
    items.forEach(function (item, idx2) {
        var modalId = 'modal-' + idx + '-' + idx2;

        var $elem = $('.menu__images-template').clone().removeClass('menu__images-template').attr('style', '');

        $elem.find('a').attr('href', '#' + modalId);
        $elem.find('img').attr('src', item[0]);
        $elem.find('.menu__images-title-text').text(item[1]);

        $elem.appendTo($menu);

        var $modal = $('.modal__template').clone().removeClass('modal__template');

        $modal.attr('id', modalId);

        $modal.find('.modal__title').text(item[1]);
        $modal.find('.modal__img').attr('src', item[0]);
        $modal.find('.modal__content').text(item[2]);

        $modal.appendTo('body');
    });
});

$(function() {
    for (var k in elementAnimated) {
        let inViewport = isItemInViewport($(k));

        if (elementAnimated[k] !== inViewport) {
            if (inViewport) { // open animation
                $(k).animate({ opacity: 1 }, 700);
            } else { // closing animation
                $(k).animate({ opacity: 0 }, 700);
            }

            elementAnimated[k] = inViewport;
        }
    }

    changeMenuSection(0);
});