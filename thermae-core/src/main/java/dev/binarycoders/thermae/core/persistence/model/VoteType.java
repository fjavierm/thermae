/*
 * MIT License
 *
 * Copyright (c) 2020 fjavierm
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package dev.binarycoders.thermae.core.persistence.model;

import dev.binarycoders.thermae.core.exception.ThermaeException;

import java.util.Arrays;

public enum VoteType {
    UP(1),
    DOWN(-1);

    private int direction;

    VoteType(int direction) {
        this.direction = direction;
    }

    public static VoteType lookup(final Integer direction) {
        return Arrays.stream(VoteType.values())
            .filter(voteType -> direction == voteType.direction)
            .findFirst()
            .orElseThrow(() -> new ThermaeException(String.format("Direction %d does not exist.", direction)));
    }

    public int getDirection() {
        return direction;
    }
}
