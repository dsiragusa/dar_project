/**
 * Created by daniele on 11/11/15.
 */

(function($) {
    $(document).ready(function () {
        var $tags = $('#tags');
        var availableIds = {};
        $tags.find('option').each(function(i, e) {
            availableIds[$(e).val()] = true;
        });

        $tags.on('select2:select', function (e) {
            var $select2Opt = $('.select2-selection__choice[title="' + e.params.data.id + '"]');
            var $option = $(this).find('option[value="' + e.params.data.id + '"]');
            if ( ! $option.data('select2-tag')) {
                return;
            }

            $tags.prop('disabled', true);
            var $loader = $('<li class="select2-selection__choice" id="select-loader">Loading</li>');

            $select2Opt.after($loader);

            $.post(
                baseURL + 'tag/create',
                {
                    name: $option.val()
                },
                function (result) {
                    $tags.prop('disabled', false);
                    var newTagId = result.id;
                    $('#select-loader').remove();
                    $select2Opt.remove();
                    $option.remove();

                    if (result.id == null) {
                        return;
                    }

                    if (availableIds[newTagId] === undefined) {
                        availableIds[newTagId] = true;
                        $('<option value="' + result.id + '">' + result.name + '</option>').appendTo($tags);

                    }

                    var values = $tags.val() ? $tags.val() : [];
                    values.push(newTagId);
                    $tags.val(values).trigger('change');
                });
        });
    });
})(jQuery);